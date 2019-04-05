package org.uma.jmetal.runner.multiobjective;

import org.uma.jmetal.runner.multiobjective.aNSGARunner;
import org.uma.jmetal.runner.multiobjective.NSGAIIRunner;
import org.uma.jmetal.runner.multiobjective.NSGAIIIRunner;
import org.uma.jmetal.util.AbstractAlgorithmRunner;

import java.io.FileNotFoundException;


public class aaRunner extends AbstractAlgorithmRunner {
    //        String problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
//        referenceParetoFront = "/pareto_fronts/ZDT1.pf";
    final private static String[][] problemWithParetoFrontRef = {
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT6", "/pareto_fronts/ZDT6.pf"}
    };
    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
            aNSGARunner.main(problemWithParetoFrontRef[i]);
        }
    }

}
