package org.uma.jmetal.runner.multiobjective;
//NSGAIII
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.ansga.aNSGABuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.*;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Class to configure and run the aNSGARunner algorithm
 */
public class aNSGARunner extends AbstractAlgorithmRunner  {
    /**
     * @param args Command line arguments.
     * @throws JMetalException
     * @throws FileNotFoundException
     * Invoking command:
    java org.uma.jmetal.runner.multiobjective.NSGAIIRunner problemName [referenceFront]
     */
    public static void main(String[] args) throws JMetalException, FileNotFoundException {
//        declares the type of the problem to solve
        Problem<DoubleSolution> problem;
        Algorithm<List<DoubleSolution>> algorithm;
        CrossoverOperator<DoubleSolution> crossover;
        MutationOperator<DoubleSolution> mutation;
        SelectionOperator<List<DoubleSolution>, DoubleSolution> selection;
        String referenceParetoFront = "" ;

//        load a problem
        String problemName ;
        if (args.length == 1) {
        problemName = args[0];
        } else if (args.length == 2) {
        problemName = args[0] ;
        referenceParetoFront = args[1] ;
        } else {
        problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
        referenceParetoFront = "jmetal-problem/src/test/resources/pareto_fronts/ZDT1.pf" ;
        }

        problem = ProblemUtils.<DoubleSolution> loadProblem(problemName);

//        operators and algorithm are configured
        double crossoverProbability = 0.9 ;
        double crossoverDistributionIndex = 20.0 ;
        crossover = new SBXCrossover(crossoverProbability, crossoverDistributionIndex) ;

        double mutationProbability = 1.0 / problem.getNumberOfVariables() ;
        double mutationDistributionIndex = 20.0 ;
        mutation = new PolynomialMutation(mutationProbability, mutationDistributionIndex) ;

        selection = new BinaryTournamentSelection<DoubleSolution>(
        new RankingAndCrowdingDistanceComparator<DoubleSolution>());

//        from NSGAIIRunner
//        algorithm = new aNSGABuilder<DoubleSolution>(problem, crossover, mutation)
//        .setSelectionOperator(selection)
//        .setMaxEvaluations(25000)
//        .setPopulationSize(100)
//        .build() ;

//        from  NSGAIIIRunner
        algorithm = new aNSGABuilder<>(problem, crossover, mutation)
//                .setCrossoverOperator(crossover)
//                .setMutationOperator(mutation)
                .setSelectionOperator(selection)
//                .setMaxIterations(300)
                .build() ;



//        print
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
        .execute() ;

        List<DoubleSolution> population = algorithm.getResult() ;
        long computingTime = algorithmRunner.getComputingTime() ;

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);
        if (!referenceParetoFront.equals("")) {
        printQualityIndicators(population, referenceParetoFront) ;
        }
    }
}
