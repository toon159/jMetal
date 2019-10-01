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
import java.lang.reflect.Array;
import java.util.*;


public class aaRunner extends AbstractAlgorithmRunner {
//        String problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
//        referenceParetoFront = "/pareto_fronts/ZDT1.pf";


    //    4 maxdrop 5 droppercent
    static int max_drop = 10;
    static double drop_percent = 1;

    final private static String[][] problemWithParetoFrontRef = {

            //            500
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},

            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/pareto_fronts/DTLZ5.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},

            {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},

            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},


            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},

    };

    public static void main(String[] args) throws FileNotFoundException {
        int[] max_generation = {300, 500, 1000, 1500, 2000};
        int n = 10;
        int iter;
        ArrayList<double[]> list = new ArrayList();
        list.add(new double[]{});
        int number_of_algo = 18;

        for (int max_gen = 0; max_gen < max_generation.length; max_gen++) {
            for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
                iter = i;
                problemWithParetoFrontRef[i][2] = ""+max_generation[max_gen];
                double[] hv = new double[number_of_algo];

    /*
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < number_of_algo; k++){
                        problemWithParetoFrontRef[i][5] = "" + (0.1 * k);
                        hv[k] += aNSGARunner.main(problemWithParetoFrontRef[i]);
                    }*/
                double[] nsga2, nsga3, ansga, nsga2hv, nsga3hv, ansgahv, nsga2igd, nsga3igd, ansgaigd;
                nsga2hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga3hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                ansgahv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga2igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga3igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                ansgaigd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
//                for (int j = 6; j < 18; j++) {
//                    hv[j] = 0;
//                }

                for (int j = 0; j < n; j++) {

                    nsga2 = NSGAIIRunner.main(problemWithParetoFrontRef[i]);
                    nsga3 = NSGAIIIRunner.main(problemWithParetoFrontRef[i]);
                    ansga = aNSGARunner.main(problemWithParetoFrontRef[i]);
                    hv[0] += nsga2[0];
                    hv[1] += nsga3[0];
                    hv[2] += ansga[0];
                    hv[3] += nsga2[1];
                    hv[4] += nsga3[1];
                    hv[5] += ansga[1];

//                    check min
                    if(nsga2[0] < nsga2hv[0]) {
                        nsga2hv[0] = nsga2[0];
                    }
                    if(nsga3[0] < nsga3hv[0]) {
                        nsga3hv[0] = nsga3[0];
                    }
                    if(ansga[0] < ansgahv[0]) {
                        ansgahv[0] = ansga[0];
                    }
                    if(nsga2[1] < nsga2igd[0]) {
                        nsga2igd[0] = nsga2[1];
                    }
                    if(nsga3[1] < nsga3igd[0]) {
                        nsga3igd[0] = nsga3[1];
                    }
                    if(ansga[1] < ansgaigd[0]) {
                        ansgaigd[0] = ansga[1];
                    }

//                    check max
                    if(nsga2[0] > nsga2hv[1]) {
                        nsga2hv[1] = nsga2[0];
                    }
                    if(nsga3[0] > nsga3hv[1]) {
                        nsga3hv[1] = nsga3[0];
                    }
                    if(ansga[0] > ansgahv[1]) {
                        ansgahv[1] = ansga[0];
                    }
                    if(nsga2[1] > nsga2igd[1]) {
                        nsga2igd[1] = nsga2[1];
                    }
                    if(nsga3[1] > nsga3igd[1]) {
                        nsga3igd[1] = nsga3[1];
                    }
                    if(ansga[1] > ansgaigd[1]) {
                        ansgaigd[1] = ansga[1];
                    }

                }
                for (int j = 0; j < 6; j++) {
                    hv[j] /= n;
                }
                hv[6] = nsga2hv[0];
                hv[7] = nsga3hv[0];
                hv[8] = ansgahv[0];
                hv[9] = nsga2igd[0];
                hv[10] = nsga3igd[0];
                hv[11] = ansgaigd[0];

                hv[12] = nsga2hv[1];
                hv[13] = nsga3hv[1];
                hv[14] = ansgahv[1];
                hv[15] = nsga2igd[1];
                hv[16] = nsga3igd[1];
                hv[17] = ansgaigd[1];

                list.add(hv);
                JMetalLogger.logger.info("max_Gen: " + max_generation[max_gen] + " => " + (iter + 1) + " / " + problemWithParetoFrontRef.length + " (" + Math.round((iter + 1.0) / problemWithParetoFrontRef.length * 100) + "%)");
                for (Double d : hv) {
                    System.out.println(d);
                }

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