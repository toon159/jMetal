package org.uma.jmetal.runner.multiobjective;

import org.uma.jmetal.runner.multiobjective.aNSGARunner;
import org.uma.jmetal.runner.multiobjective.NSGAIIRunner;
import org.uma.jmetal.runner.multiobjective.NSGAIIIRunner;
import org.uma.jmetal.util.AbstractAlgorithmRunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class aaRunner extends AbstractAlgorithmRunner {
    //        String problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
//        referenceParetoFront = "/pareto_fronts/ZDT1.pf";
    final private static String[][] problemWithParetoFrontRef = {
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT5", "/pareto_fronts/ZDT4.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT6", "/pareto_fronts/ZDT4.pf"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT7", "/pareto_fronts/ZDT4.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "pareto_fronts/DTLZ1.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "pareto_fronts/DTLZ2.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "pareto_fronts/DTLZ3.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "pareto_fronts/DTLZ4.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "pareto_fronts/DTLZ5.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ6", "pareto_fronts/DTLZ6.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "pareto_fronts/DTLZ7.3D.pf"}
    };
    private static Object[][] data;
    ArrayList list = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
            double hv;
            hv = aNSGARunner.main(problemWithParetoFrontRef[i]);
        }
        aaExcel excel = new aaExcel(data);
        excel.toExcel();
    }

}
