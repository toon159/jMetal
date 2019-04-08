package org.uma.jmetal.algorithm.multiobjective.ansga;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.comparator.DominanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;

import java.util.Comparator;
import java.util.List;

/**
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class aNSGABuilder<S extends Solution<?>> implements AlgorithmBuilder<aNSGA<S>> {
    public enum NSGAIIVariant {NSGAII, SteadyStateNSGAII, Measures, NSGAII45}

    /**
     * NSGAIIBuilder class
     */
    private final Problem<S> problem;
    private int maxIterations; // maxIterations in NSGAIII
    private int populationSize;
    private CrossoverOperator<S> crossoverOperator;
    private MutationOperator<S> mutationOperator;
    private SelectionOperator<List<S>, S> selectionOperator;

    private SolutionListEvaluator<S> evaluator;

    private Comparator<S> dominanceComparator;

    private NSGAIIVariant variant;

    public String getReferenceParetoFront() {
        return referenceParetoFront;
    }

    private String referenceParetoFront = "";


    public aNSGABuilder<S> setReferenceParetoFront(String referenceParetoFront) {
        this.referenceParetoFront = referenceParetoFront;
        return this;
    }



    /**
     * aNSGABuilder constructor
     */
    public aNSGABuilder(Problem<S> problem) {
        this.problem = problem;
//        maxIterations = 25000;
        populationSize = 100;
//        selectionOperator = new BinaryTournamentSelection<S>(new RankingAndCrowdingDistanceComparator<>());
        evaluator = new SequentialSolutionListEvaluator<S>();
        dominanceComparator = new DominanceComparator<>();

        this.variant = NSGAIIVariant.NSGAII;
    }


    public aNSGABuilder<S> setMaxIterations(int maxIterations) {
        if (maxIterations < 0) {
            throw new JMetalException("maxIterations is negative: " + maxIterations);
        }
        this.maxIterations = maxIterations;

        return this;
    }

    public aNSGABuilder<S> setPopulationSize(int populationSize) {
        if (populationSize < 0) {
            throw new JMetalException("Population size is negative: " + populationSize);
        }

        this.populationSize = populationSize;

        return this;
    }

    public aNSGABuilder<S> setSelectionOperator(SelectionOperator<List<S>, S> selectionOperator) {
        if (selectionOperator == null) {
            throw new JMetalException("selectionOperator is null");
        }
        this.selectionOperator = selectionOperator;

        return this;
    }

    public aNSGABuilder<S> setCrossoverOperator(CrossoverOperator<S> crossoverOperator) {
        this.crossoverOperator = crossoverOperator;
        return this;
    }

    public aNSGABuilder<S> setMutationOperator(MutationOperator<S> mutationOperator) {
        this.mutationOperator = mutationOperator;
        return this;
    }

    public aNSGABuilder<S> setSolutionListEvaluator(SolutionListEvaluator<S> evaluator) {
        if (evaluator == null) {
            throw new JMetalException("evaluator is null");
        }
        this.evaluator = evaluator;

        return this;
    }

    public aNSGABuilder<S> setDominanceComparator(Comparator<S> dominanceComparator) {
        if (dominanceComparator == null) {
            throw new JMetalException("dominanceComparator is null");
        }
        this.dominanceComparator = dominanceComparator;

        return this;
    }


    public aNSGABuilder<S> setVariant(NSGAIIVariant variant) {
        this.variant = variant;

        return this;
    }

    /* Getters */
    public Problem<S> getProblem() {
        return problem;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public CrossoverOperator<S> getCrossoverOperator() {
        return crossoverOperator;
    }

    public MutationOperator<S> getMutationOperator() {
        return mutationOperator;
    }

    public SelectionOperator<List<S>, S> getSelectionOperator() {
        return selectionOperator;
    }

    public SolutionListEvaluator<S> getEvaluator() {
        return evaluator;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public aNSGA<S> build() {
        return new aNSGA<>(this);
    }
}
