package org.uma.jmetal.algorithm.multiobjective.ansga;

import com.opencsv.CSVWriter;
import org.uma.jmetal.algorithm.impl.AbstractGeneticAlgorithm;
import org.uma.jmetal.algorithm.multiobjective.ansga.util.EnvironmentalSelection;
import org.uma.jmetal.algorithm.multiobjective.ansga.util.ReferencePoint;
import org.uma.jmetal.operator.impl.selection.RankingAndCrowdingSelection;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.Solution;
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

import java.io.*;
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

    final protected double startingTemp = 1;
    protected double minTemp = 0.0;
    protected double temp = startingTemp;
    final protected int startingTempCounter = 100;
    protected int tempCounter = startingTempCounter;
    protected double coolingRate = 10;
    protected double numIterAnnealing = 100;
    protected double[] result;
    protected double hv = 0;
    protected double newHv = 1;
    protected double igd = 1000;
    protected double newIgd = 1;
    protected double deltaE;
    protected double deltaI;
    protected double currentE;
    protected double nextE;
    protected JMetalRandom random;
    protected String referenceParetoFront;
    private double deltaQ = 1;
    boolean change = false;
    List<String[]> data = new ArrayList<>();
    protected int hvDropCount = 0;
    protected int maxDrop = 5;
    protected double hvDropPercent = -5;

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
        maxDrop = builder.getMaxDrop();
        hvDropPercent = builder.getHvDropPercent();


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
        iterations = 1;
//        System.out.println("start");
    }

    @Override
    protected void updateProgress() {
        iterations++;
    }

    @Override
    protected boolean isStoppingConditionReached() {
        if (iterations >= maxIterations) {
            // first create file object for file placed at location
            // specified by filepath
            File file = new File("hv.csv");

            try {
                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER);
                writer.writeNext(new String[]{"newHv", "deltaE", "temp", "temp+deltaE", "change"});
                writer.writeAll(data);

                // closing writer connection
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return iterations >= maxIterations;
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
            result = getHVandIGD(fronts.get(0));
        } else {
            result = getHVandIGD(pop);
        }

        newHv = result[0];
        newIgd = result[1];

        //        if solution is worse, drop count + 1
        if (deltaE < 0) {
            hvDropCount++;
        } else {
            hvDropCount = 0;
        }

        if(iterations == 0){
            deltaE = 0;
            change = false;
        } else {
            deltaE = newHv - hv;
            deltaI = newIgd - igd;
            if (shouldChange(temp, deltaE)) {
                change = !change;
            }
        }



//        System.out.println(newHv + "," + deltaE + "," + temp + "," + (temp+deltaE) + "," + (change?1:0));
        String[] row = new String[]{""+newHv,""+deltaE,""+temp,""+(temp+deltaE),(change?"1":"0"), ""+newIgd, ""+deltaI};
        data.add(row);

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
//        System.out.print(change?'2':'3');
//        JMetalLogger.logger.info("" + pop.size());

//        if (!shouldAccept(temp, deltaE)){
//            pop = population;
//        }

//        change temp if deltaE is better.
        if(change == false) {
            temp = 1 - newHv;
        } else {
            temp = 1 - newHv;
//            temp -= coolingRate;
//            tempCounter = 30;
        }
        hv = newHv;
        igd = newIgd;
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
        return temp + deltaE;
    }
//    private boolean shouldAccept(double temp, double deltaE) {
//        return true;
//    }

    private boolean shouldChange(double temp, double deltaE) {
//        double randomValue = JMetalRandom.getInstance().nextDouble(0, 1);
//        double probOfAccept = probabilityOfAcceptance(temp, deltaE);  // start from high to low (1 -> 0)
////        probOfAccept < randomValue //at high temp can accept many cases
//
//        if (deltaE >= 0) {  // if new hv is better
//            return false;  // don't change
//        } else {
//            if (probOfAccept > 1) {  // if new hv is lower but acceptable
//                return false;  // don't change
//            } else {  // if new hv is unacceptable
//                return true;  // do change
//            }
//        }



//        if drop so much then change
//        if drop so much but igd is better then don't change
//        and
        if ((deltaE/hv)*100 < hvDropPercent && deltaI < 0) {
            return true;
        } else if (hvDropCount >= maxDrop) {
            hvDropCount = 0;
            return true;
        }
        return false;
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

    private double[] getHVandIGD(List<S> population) {
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
        double hv, igd;
        hv = new PISAHypervolume<PointSolution>(normalizedReferenceFront).evaluate(normalizedPopulation);
        igd = new InvertedGenerationalDistance<PointSolution>(normalizedReferenceFront).evaluate(normalizedPopulation);
        double[] result = {hv, igd};
        return result;
    }
}
