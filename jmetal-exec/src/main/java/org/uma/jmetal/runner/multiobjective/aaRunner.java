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
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT6", "/pareto_fronts/ZDT6.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/pareto_fronts/DTLZ5.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ6", "/pareto_fronts/DTLZ6.3D.pf"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/pareto_fronts/DTLZ7.3D.pf"}
    };
    private static Object[][] data;


    public static void main(String[] args) throws FileNotFoundException {
        int n = 1;
        ArrayList<double[]> list = new ArrayList();
        list.add(new double[] {2.5, 2, 3});
        for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
            double[] hv = new double[3];
            for (int j = 0; j < n; j++) {
                hv[0] += aNSGARunner.main(problemWithParetoFrontRef[i]);
//                hv[1] += NSGAIIRunner.main(problemWithParetoFrontRef[i]);
//                hv[2] += NSGAIIIRunner.main(problemWithParetoFrontRef[i]);
                System.out.println();
            }
            hv[0] /= n;
//            hv[1] /= n;
//            hv[2] /= n;
            list.add(hv);
        }
        aaExcel excel = new aaExcel(list);
        excel.toExcel();
    }

}
