package org.uma.jmetal.runner.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.runner.multiobjective.aNSGARunner;
import org.uma.jmetal.runner.multiobjective.hNSGARunner;
import org.uma.jmetal.runner.multiobjective.NSGAIIRunner;
import org.uma.jmetal.runner.multiobjective.NSGAIIIRunner;
import org.uma.jmetal.util.AbstractAlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class aaRunner extends AbstractAlgorithmRunner {
    //        String problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
//        referenceParetoFront = "/pareto_fronts/ZDT1.pf";
    final private static String[][] problemWithParetoFrontRef = {
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.2D.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.2D.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.2D.pf", "300", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.2D.pf", "300", "2", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf", "300", "3", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf", "300", "3", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf", "300", "3", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf", "300", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf", "300", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf", "300", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf", "300", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf", "300", "3", "false", "5"},

            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.2D.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.2D.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.2D.pf", "500", "2", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.2D.pf", "500", "2", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf", "500", "3", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf", "500", "3", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf", "500", "3", "false", "5"},
//            {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf", "500", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf", "500", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf", "500", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf", "500", "3", "false", "5"},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf", "500", "3", "false", "5"},
    };

    public static void main(String[] args) throws FileNotFoundException {
        int n = 30;
        ArrayList<double[]> list = new ArrayList();
        list.add(new double[] {2, 3, 2.5, 2.55});
        for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
            double[] hv = new double[4];

            for (int j = 0; j < n; j++) {
                hv[0] += NSGAIIRunner.main(problemWithParetoFrontRef[i]);
                hv[1] += NSGAIIIRunner.main(problemWithParetoFrontRef[i]);
                hv[2] += hNSGARunner.main(problemWithParetoFrontRef[i]);

                problemWithParetoFrontRef[i][5] = "-1";
                hv[3] += hNSGARunner.main(problemWithParetoFrontRef[i]);
                problemWithParetoFrontRef[i][5] = "5";
            }
            hv[0]/=n;
            hv[1]/=n;
            hv[2]/=n;
            hv[3]/=n;
            list.add(hv);
            JMetalLogger.logger.info(""+ (i+1) +" / " + problemWithParetoFrontRef.length + " (" + Math.round((i+1.0)  / problemWithParetoFrontRef.length * 100) + "%)");
        }



        aaExcel excel = new aaExcel(list);
        excel.toExcel();
    }

}
