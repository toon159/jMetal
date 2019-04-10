package org.uma.jmetal.algorithm.multiobjective.ansga;

import org.uma.jmetal.algorithm.impl.AbstractGeneticAlgorithm;
import org.uma.jmetal.algorithm.multiobjective.ansga.util.EnvironmentalSelection;
import org.uma.jmetal.algorithm.multiobjective.ansga.util.ReferencePoint;
import org.uma.jmetal.operator.impl.selection.RankingAndCrowdingSelection;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.SolutionListUtils;
import org.uma.jmetal.util.comparator.DominanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.imp.ArrayFront;
import org.uma.jmetal.util.front.util.FrontNormalizer;
import org.uma.jmetal.util.front.util.FrontUtils;
import org.uma.jmetal.util.point.PointSolution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import org.uma.jmetal.util.pseudorandom.RandomGenerator;
import org.uma.jmetal.util.solutionattribute.Ranking;
import org.uma.jmetal.util.solutionattribute.impl.DominanceRanking;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
@SuppressWarnings("serial")
public class aNSGA<S extends Solution<?>> extends AbstractGeneticAlgorithm<S, List<S>> {
    protected int maxIterations;

    protected final SolutionListEvaluator<S> evaluator;

    protected int iterations;
    protected Comparator<S> dominanceComparator;

    protected Vector<Integer> numberOfDivisions;
    protected List<ReferencePoint<S>> referencePoints = new Vector<>();

    protected boolean isNSGAII;

    final protected double startingTemp = 100;
    protected double minTemp = 0.0;
    protected double temp = startingTemp;
    final protected int startingTempCounter = 100;
    protected int tempCounter = startingTempCounter;
    protected double coolingRate = 10;
    protected double numIterAnnealing = 100;
    protected double result;
    protected double hv = 1;
    protected double newHv = 1;
    protected double deltaE;
    protected double currentE;
    protected double nextE;
    protected JMetalRandom random;
    protected String referenceParetoFront;
    private double deltaQ = 1;
    boolean change = false;

    private RandomGenerator<Double> randomGenerator ;
    /**
     * Constructor
     */
    public aNSGA(aNSGABuilder<S> builder) { // can be created from the NSGAIIIBuilder within the same package
        super(builder.getProblem());
        maxIterations = builder.getMaxIterations();

        crossoverOperator = builder.getCrossoverOperator();
        mutationOperator = builder.getMutationOperator();
        selectionOperator = builder.getSelectionOperator();
        maxPopulationSize = builder.getPopulationSize();
        referenceParetoFront = builder.getReferenceParetoFront();
        evaluator = builder.getEvaluator();
        dominanceComparator = new DominanceComparator<>();


        /// NSGAIII
        numberOfDivisions = new Vector<>(1);
        numberOfDivisions.add(12); // 12 Default value for 3D problems


        (new ReferencePoint<S>()).generateReferencePoints(referencePoints, getProblem().getNumberOfObjectives(), numberOfDivisions);

//    int populationSize = referencePoints.size();
//    while (populationSize%4>0) {
//      populationSize++;
//    }

//        setMaxPopulationSize(population);

//        JMetalLogger.logger.info("rpssize: " + referencePoints.size());
//        JMetalLogger.logger.info("popsize: " + maxPopulationSize);

    }

    @Override
    protected void initProgress() {
        temp = 300;
    }

    @Override
    protected void updateProgress() {
        temp--;
    }

    @Override
    protected boolean isStoppingConditionReached() {
        return temp <= minTemp;
    }

    @Override
    protected List<S> evaluatePopulation(List<S> population) {
        population = evaluator.evaluate(population, getProblem());

        return population;
    }


    @Override
    protected List<S> selection(List<S> population) {
        List<S> matingPopulation = new ArrayList<>(population.size());
        for (int i = 0; i < getMaxPopulationSize(); i++) {
            S solution = selectionOperator.execute(population);
            matingPopulation.add(solution);
        }

        return matingPopulation;
    }

    @Override
    protected List<S> reproduction(List<S> population) {
        List<S> offspringPopulation = new ArrayList<>(getMaxPopulationSize());
        for (int i = 0; i < getMaxPopulationSize(); i += 2) {
            List<S> parents = new ArrayList<>(2);
            parents.add(population.get(i));
            parents.add(population.get(Math.min(i + 1, getMaxPopulationSize() - 1)));

            List<S> offspring = crossoverOperator.execute(parents);

            mutationOperator.execute(offspring.get(0));
            mutationOperator.execute(offspring.get(1));

            offspringPopulation.add(offspring.get(0));
            offspringPopulation.add(offspring.get(1));
        }
        return offspringPopulation;
    }

    private List<ReferencePoint<S>> getReferencePointsCopy() {
        List<ReferencePoint<S>> copy = new ArrayList<>();
        for (ReferencePoint<S> r : this.referencePoints) {
            copy.add(new ReferencePoint<>(r));
        }
        return copy;
    }

    @Override
    protected List<S> replacement(List<S> population, List<S> offspringPopulation) {
        List<S> jointPopulation = new ArrayList<>();
        jointPopulation.addAll(population);
        jointPopulation.addAll(offspringPopulation);

//    Add as most ranks as possible
        Ranking<S> ranking = computeRanking(jointPopulation);
        //List<Solution> pop = crowdingDistanceSelection(ranking);
        List<S> pop = new ArrayList<>();
        List<List<S>> fronts = new ArrayList<>();
        int rankingIndex = 0;
        int candidateSolutions = 0;
//    while the number of solutions is less than pop
        while (candidateSolutions < getMaxPopulationSize()) {
//      add fronts
            fronts.add(ranking.getSubfront(rankingIndex));
            candidateSolutions += ranking.getSubfront(rankingIndex).size();
//      if current rank + solutions <= max pop then add that rank
            if ((pop.size() + ranking.getSubfront(rankingIndex).size()) <= getMaxPopulationSize())
                addRankedSolutionsToPopulation(ranking, rankingIndex, pop);
//      increment ranking index
            rankingIndex++;
        }
//        System.out.println("" + JMetalRandom.getInstance().nextDouble(0, 1));
//        calculate the energy

//        if first front > max population then use that front instead
        if (pop.size() == 0) {
//            late, accept a solution which increase hv
            result = getHypervolume(fronts.get(0));
        } else {
            result = getHypervolume(pop);
        }

        newHv = result;
        deltaE = hv - newHv;


        if (shouldChange(temp, deltaE)) {
            change = !change;
        }
        if (change) {
//      2
            isNSGAII = true;
            RankingAndCrowdingSelection<S> rankingAndCrowdingSelection;
            rankingAndCrowdingSelection = new RankingAndCrowdingSelection<>(getMaxPopulationSize(), dominanceComparator);
            pop = rankingAndCrowdingSelection.execute(jointPopulation);
        } else {
//      3
            isNSGAII = false;
//      System.out.println(3);
            // A copy of the reference list should be used as parameter of the environmental selection
            EnvironmentalSelection<S> selection =
                    new EnvironmentalSelection<>(fronts, getMaxPopulationSize(), getReferencePointsCopy(),
                            getProblem().getNumberOfObjectives());
            pop = selection.execute(pop);
        }
//        JMetalLogger.logger.info("" + pop.size());

        if (!shouldAccept(temp, deltaE)){
            pop = population;
        }
/*
//        change temp every x generations.
        if(tempCounter > 0) {
            tempCounter--;
        } else {
            temp -= coolingRate;
            tempCounter = 30;
        }*/

        hv = newHv;
        return pop;
    }

    @Override
    public List<S> getResult() {
        return getNonDominatedSolutions(getPopulation());
    }

    protected Ranking<S> computeRanking(List<S> solutionList) {
        Ranking<S> ranking = new DominanceRanking<>();
        ranking.computeRanking(solutionList);

        return ranking;
    }

    protected void addRankedSolutionsToPopulation(Ranking<S> ranking, int rank, List<S> population) {
        List<S> front;

        front = ranking.getSubfront(rank);

        for (int i = 0; i < front.size(); i++) {
            population.add(front.get(i));
        }
    }

    protected List<S> getNonDominatedSolutions(List<S> solutionList) {
        return SolutionListUtils.getNondominatedSolutions(solutionList);
    }

    @Override
    public String getName() {
        return "aNSGA";
    }

    @Override
    public String getDescription() {
        return "Adaptive-Selection Nondominated Sorting Genetic Algorithm";
    }

    private double probabilityOfAcceptance(double temp, double deltaE) {
        return Math.exp(deltaE / temp);
    }
    private boolean shouldAccept(double temp, double deltaE) {
        return true;
    }

    private boolean shouldChange(double temp, double deltaE) {
        double randomValue = JMetalRandom.getInstance().nextDouble(0, 1);
        double probOfAccept = probabilityOfAcceptance(temp, deltaE);
//        probOfAccept < randomValue //at high temp can accept many cases
        return (deltaE < 0) || probOfAccept > randomValue ;
    }

    private double getHypervolume(List<S> population) {
        Front referenceFront = null;
        try {
            referenceFront = new ArrayFront(referenceParetoFront);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront) ;
        Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront) ;
        Front normalizedFront = frontNormalizer.normalize(new ArrayFront(population)) ;
        List<PointSolution> normalizedPopulation = FrontUtils
                .convertFrontToSolutionList(normalizedFront) ;
//        JMetalLogger.logger.info("Spread (N)      : " + new Spread<PointSolution>(normalizedReferenceFront).evaluate(normalizedPopulation));
        double hv;
        hv = new PISAHypervolume<PointSolution>(normalizedReferenceFront).evaluate(normalizedPopulation);
        return hv;
    }
}
