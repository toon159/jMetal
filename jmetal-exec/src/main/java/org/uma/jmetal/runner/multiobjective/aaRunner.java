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
    static int max_drop = 10;
    static double drop_percent = 1;

    final private static String[][] problemWithParetoFrontRef = {

            //            500
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.4D.pf", "500", "4", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.4D.pf", "500", "4", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.4D.pf", "500", "4", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.4D.pf", "500", "4", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/real_front/DTLZ7-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/real_front/DTLZ7-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},

//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-8-PF.txt", "500", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-8-PF.txt", "500", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-8-PF.txt", "500", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-8-PF.txt", "500", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-8-PF.txt", "500", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/real_front/DTLZ7-8-PF.txt", "500", "8", ""+max_drop, ""+drop_percent},

        //            1000
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
        {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.3D.pf", "1000", "3", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.4D.pf", "1000", "4", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.4D.pf", "1000", "4", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.4D.pf", "1000", "4", ""+max_drop, ""+drop_percent},
//        {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.4D.pf", "1000", "4", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-3-PF.txt", "1000", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-3-PF.txt", "1000", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-3-PF.txt", "1000", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-3-PF.txt", "1000", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-3-PF.txt", "1000", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/real_front/DTLZ7-3-PF.txt", "1000", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-5-PF.txt", "1000", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-5-PF.txt", "1000", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-5-PF.txt", "1000", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-5-PF.txt", "1000", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-5-PF.txt", "1000", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/real_front/DTLZ7-5-PF.txt", "1000", "5", ""+max_drop, ""+drop_percent},

//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-8-PF.txt", "1000", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-8-PF.txt", "1000", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-8-PF.txt", "1000", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-8-PF.txt", "1000", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-8-PF.txt", "1000", "8", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7", "/real_front/DTLZ7-8-PF.txt", "1000", "8", ""+max_drop, ""+drop_percent},

    };

    public static void main(String[] args) throws FileNotFoundException {
        int n = 30;
        int iter = 0;
        ArrayList<double[]> list = new ArrayList();
        list.add(new double[]{});
        int number_of_algo = 6;
        double[] hv = new double[number_of_algo];
        for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
            iter = i;
/*
            for (int j = 0; j < n; j++) {
				for (int k = 0; k < number_of_algo; k++){
				    problemWithParetoFrontRef[i][5] = "" + (0.1 * k);
                    hv[k] += aNSGARunner.main(problemWithParetoFrontRef[i]);
				}*/
            for (int j = 0; j < n; j++) {
                double[] nsga2, nsga3, ansga;
                nsga2 = NSGAIIRunner.main(problemWithParetoFrontRef[i]);
                nsga3 = NSGAIIIRunner.main(problemWithParetoFrontRef[i]);
                ansga = aNSGARunner.main(problemWithParetoFrontRef[i]);
                hv[0] += nsga2[0];
                hv[1] += nsga3[0];
                hv[2] += ansga[0];
                hv[3] += nsga2[1];
                hv[4] += nsga3[1];
                hv[5] += ansga[1];
            }
            for (int j = 0; j < number_of_algo; j++) {
                hv[j] /= n;
            }
            list.add(hv);
            JMetalLogger.logger.info("" + (iter + 1) + " / " + problemWithParetoFrontRef.length + " (" + Math.round((iter + 1.0) / problemWithParetoFrontRef.length * 100) + "%)");
            for (Double d : hv) {
                System.out.println(d);
            }

        }
//
//                problemWithParetoFrontRef[i][5] = "-1";
//                hv[3] += hNSGARunner.main(problemWithParetoFrontRef[i]);
//                problemWithParetoFrontRef[i][5] = "5";



//            hv[3]/=n;




        aaExcel excel = new aaExcel(list);
        excel.toExcel();
    }

}