package org.uma.jmetal.runner.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.ansga.aNSGA;
import org.uma.jmetal.algorithm.multiobjective.ansga.aNSGABuilder;
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
    
//    4 maxdrop 5 droppercent
    final private static String[][] problemWithParetoFrontRef = {

        //            300
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.2D.pf", "300", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf", "300", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.4D.pf", "300", "4", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.4D.pf", "300", "4", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.4D.pf", "300", "4", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.4D.pf", "300", "4", "10", "5"},

//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.6D.pf", "300", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.6D.pf", "300", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.6D.pf", "300", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.6D.pf", "300", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.8D.pf", "300", "8", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.8D.pf", "300", "8", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.8D.pf", "300", "8", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.8D.pf", "300", "8", "5", "5"},

//            500
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT1", "/pareto_fronts/ZDT1.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT2", "/pareto_fronts/ZDT2.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT3", "/pareto_fronts/ZDT3.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.zdt.ZDT4", "/pareto_fronts/ZDT4.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.2D.pf", "500", "2", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf", "500", "3", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.4D.pf", "500", "4", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.4D.pf", "500", "4", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.4D.pf", "500", "4", "10", "5"},
        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.4D.pf", "500", "4", "10", "5"},

//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.6D.pf", "500", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.6D.pf", "500", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.6D.pf", "500", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.6D.pf", "500", "6", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.8D.pf", "500", "8", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.8D.pf", "500", "8", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.8D.pf", "500", "8", "5", "5"},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.8D.pf", "500", "8", "5", "5"},

    };

    public static void main(String[] args) throws FileNotFoundException {
        int n = 5;
        ArrayList<double[]> list = new ArrayList();
        list.add(new double[] {});
        for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
            int number_of_algo = 50;
            double[] hv = new double[number_of_algo];

            for (int j = 0; j < n; j++) {
				for (int k = 0; k < number_of_algo; k++){
				    problemWithParetoFrontRef[i][5] = "" + (0.1 * k);
                    hv[k] += aNSGARunner.main(problemWithParetoFrontRef[i]);
				}
//                hv[0] += NSGAIIRunner.main(problemWithParetoFrontRef[i]);
//                hv[1] += NSGAIIIRunner.main(problemWithParetoFrontRef[i]);
//                hv[2] += hNSGARunner.main(problemWithParetoFrontRef[i]);
//
//                problemWithParetoFrontRef[i][5] = "-1";
//                hv[3] += hNSGARunner.main(problemWithParetoFrontRef[i]);
//                problemWithParetoFrontRef[i][5] = "5";
            }
            for (int j = 0; j < number_of_algo; j++) {
                hv[j]/=n;
            }

//            hv[3]/=n;
            list.add(hv);
            JMetalLogger.logger.info(""+ (i+1) +" / " + problemWithParetoFrontRef.length + " (" + Math.round((i+1.0)  / problemWithParetoFrontRef.length * 100) + "%)");
            for (Double d: hv){
                System.out.println(d);
            }
        }



        aaExcel excel = new aaExcel(list);
        excel.toExcel();
    }

}

class _aNSGARunner extends aNSGARunner {

}

class _aNSGA extends aNSGA {

    /**
     * Constructor
     *
     * @param builder
     */
    public _aNSGA(aNSGABuilder builder) {
        super(builder);
        int hvDropCount = 0;
        int maxDrop = 5;
        int hvDropPercent = -5;
    }
}