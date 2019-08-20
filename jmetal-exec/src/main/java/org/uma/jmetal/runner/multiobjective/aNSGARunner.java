package org.uma.jmetal.runner.multiobjective;
//NSGAIII

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.ansga.aNSGABuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.*;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Class to configure and run the aNSGARunner algorithm
 */
public class aNSGARunner extends AbstractAlgorithmRunner {
    /**
     * @param args Command line arguments.
     * @throws JMetalException
     * @throws FileNotFoundException Invoking command:
     *                               java org.uma.jmetal.runner.multiobjective.NSGAIIRunner problemName [referenceFront]
     */
    public static double[] main(String[] args) throws JMetalException, FileNotFoundException {
//        declares the type of the problem to solve
        Problem<DoubleSolution> problem;
        Algorithm<List<DoubleSolution>> algorithm;
        CrossoverOperator<DoubleSolution> crossover;
        MutationOperator<DoubleSolution> mutation;
        SelectionOperator<List<DoubleSolution>, DoubleSolution> selection;
        String referenceParetoFront = "";

        JMetalRandom.getInstance().setSeed(1);

//        String problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
//        referenceParetoFront = "/pareto_fronts/ZDT1.pf";
        String problemName = args[0];
        referenceParetoFront = args[1];
        problem = ProblemUtils.loadProblem(problemName);
        problem.setNumberOfObjectives(Integer.parseInt(args[3]));
        int maxIterations = Integer.parseInt(args[2]);

        int maxDrop = Integer.parseInt(args[4]);
        double hvDropPercent = Double.parseDouble(args[5]) * -1;

//        operators and algorithm are configured
        double crossoverProbability = 0.9;
        double crossoverDistributionIndex = 20.0;
        crossover = new SBXCrossover(crossoverProbability, crossoverDistributionIndex);

        double mutationProbability = 1.0 / problem.getNumberOfVariables();
        double mutationDistributionIndex = 20.0;
        mutation = new PolynomialMutation(mutationProbability, mutationDistributionIndex);

        selection = new BinaryTournamentSelection<DoubleSolution>(
                new RankingAndCrowdingDistanceComparator<DoubleSolution>());
//        selection = new BinaryTournamentSelection<DoubleSolution>();


//        from  NSGAIIIRunner
        algorithm = new aNSGABuilder<>(problem)
                .setCrossoverOperator(crossover)
                .setMutationOperator(mutation)
                .setSelectionOperator(selection)
                .setMaxIterations(maxIterations)
                .setPopulationSize(100)
                .setReferenceParetoFront(referenceParetoFront)
                .setMaxDrop(maxDrop)
                .setHvDropPercent(hvDropPercent)
                .build();


//        print
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
                .execute();

        List<DoubleSolution> population = algorithm.getResult();
        long computingTime = algorithmRunner.getComputingTime();

        new SolutionListOutput(population)
                .setSeparator("\t")
                .setVarFileOutputContext(new DefaultFileOutputContext("VAR.tsv"))
                .setFunFileOutputContext(new DefaultFileOutputContext("FUN.tsv"))
                .print();

//        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");
//        JMetalLogger.logger.info("Objectives values have been written to file FUN.tsv");
//        JMetalLogger.logger.info("Variables values have been written to file VAR.tsv");
//        printFinalSolutionSet(population);

        return getHVandIGD(population, referenceParetoFront);
    }
}