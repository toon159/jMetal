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
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class aaRunner extends AbstractAlgorithmRunner {
//        String problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
//        referenceParetoFront = "/pareto_fronts/ZDT1.pf";


    //    4 maxdrop 5 droppercent
    static int max_drop = 10;
    static double drop_percent = 1;

    final private static String[][] problemWithParetoFrontRef = {

            //            500
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/pareto_fronts/DTLZ1.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/pareto_fronts/DTLZ2.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/pareto_fronts/DTLZ3.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/pareto_fronts/DTLZ4.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/pareto_fronts/DTLZ5.2D.pf", "500", "2", ""+max_drop, ""+drop_percent},

            {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},

//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG1", "/pareto_fronts/WFG1.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG2", "/pareto_fronts/WFG2.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG3", "/pareto_fronts/WFG3.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG4", "/pareto_fronts/WFG4.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG5", "/pareto_fronts/WFG5.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG6", "/pareto_fronts/WFG6.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG7", "/pareto_fronts/WFG7.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG8", "/pareto_fronts/WFG8.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.wfg.WFG9", "/pareto_fronts/WFG9.3D.pf", "500", "3", ""+max_drop, ""+drop_percent},
//
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-3-PF.txt", "500", "3", ""+max_drop, ""+drop_percent},
//
//
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1", "/real_front/DTLZ1-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2", "/real_front/DTLZ2-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3", "/real_front/DTLZ3-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4", "/real_front/DTLZ4-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},
//            {"org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5", "/real_front/DTLZ5-5-PF.txt", "500", "5", ""+max_drop, ""+drop_percent},

    };

    public static void main(String[] args) throws FileNotFoundException {
        int[] max_generation = {300, 500, 1000, 1500, 2000};
        int n = 1;
        int iter;
        ArrayList<double[]> list = new ArrayList();
        list.add(new double[]{});
        int number_of_algo = 36;

        for (int max_gen = 0; max_gen < max_generation.length; max_gen++) {
            for (int i = 0; i < problemWithParetoFrontRef.length; i++) {
                iter = i;
                problemWithParetoFrontRef[i][2] = ""+max_generation[max_gen];
                double[] results = new double[number_of_algo];

    /*
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < number_of_algo; k++){
                        problemWithParetoFrontRef[i][5] = "" + (0.1 * k);
                        results[k] += aNSGARunner.main(problemWithParetoFrontRef[i]);
                    }*/
                double[] nsga2, nsga3, nsga31, nsga32, nsga33, nsga2hv, nsga3hv, nsga31hv, nsga32hv, nsga33hv, nsga2igd, nsga3igd, nsga31igd, nsga32igd, nsga33igd;
                nsga2hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga3hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga31hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga32hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga33hv = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga2igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga3igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga31igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga32igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
                nsga33igd = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
//                for (int j = 6; j < 18; j++) {
//                    results[j] = 0;
//                }

                double[] hv_nsga2_list = new double[n];
                double[] hv_nsga3_list = new double[n];
                double[] hv_nsga31_list = new double[n];
                double[] hv_nsga32_list = new double[n];
                double[] hv_nsga33_list = new double[n];
                double[] igd_nsga2_list = new double[n];
                double[] igd_nsga3_list = new double[n];
                double[] igd_nsga31_list = new double[n];
                double[] igd_nsga32_list = new double[n];
                double[] igd_nsga33_list = new double[n];

                for (int j = 0; j < n; j++) {

                    nsga2 = NSGAIIRunner.main(problemWithParetoFrontRef[i]);
                    nsga3 = NSGAIIIRunner.main(problemWithParetoFrontRef[i]);
                    nsga31 = NSGAIII1Runner.main(problemWithParetoFrontRef[i]);
                    nsga32 = NSGAIII2Runner.main(problemWithParetoFrontRef[i]);
                    nsga33 = NSGAIII3Runner.main(problemWithParetoFrontRef[i]);

                    hv_nsga2_list[j] = nsga2[0];
                    hv_nsga3_list[j] = nsga3[0];
                    hv_nsga31_list[j] = nsga31[0];
                    hv_nsga32_list[j] = nsga32[0];
                    hv_nsga33_list[j] = nsga33[0];
                    igd_nsga2_list[j] = nsga2[1];
                    igd_nsga3_list[j] = nsga3[1];
                    igd_nsga31_list[j] = nsga31[1];
                    igd_nsga32_list[j] = nsga32[1];
                    igd_nsga33_list[j] = nsga33[1];

                }
                DescriptiveStatistics da;
                for (int j = 0; j <results.length; j++) {
                    int mod10 = j%10;
                    double[] l;
//                    if(mod10 == 0){
//                        l = hv_nsga2_list;
//                    }
                    switch(mod10){
                        case 0:
                            l = hv_nsga2_list; break;
                        case 1:
                            l = hv_nsga3_list; break;
                        case 2:
                            l = hv_nsga31_list; break;
                        case 3:
                            l = hv_nsga32_list; break;
                        case 4:
                            l = hv_nsga33_list; break;
                        case 5:
                            l = igd_nsga2_list; break;
                        case 6:
                            l = igd_nsga3_list; break;
                        case 7:
                            l = igd_nsga31_list; break;
                        case 8:
                            l = igd_nsga32_list; break;
                        case 9:
                            l = igd_nsga33_list; break;
                        default:
                            l = null;
                    }
                    da = new DescriptiveStatistics(l);
                    if(j < 10){
                        results[j] = da.getMean();
                    } else if(j < 20){
                        results[j] = da.getMin();
                    } else if(j < 30){
                        results[j] = da.getMin();
                    } else {
                        results[j] = da.getPercentile(75) - da.getPercentile(25);
                    }
                }
//                da = new DescriptiveStatistics(hv_nsga2_list);
//                results[0] = da.getMean();
//                da = new DescriptiveStatistics(hv_nsga3_list);
//                results[1] = da.getMean();
//                da = new DescriptiveStatistics(hv_nsga31_list);
//                results[2] = da.getMean();
//                da = new DescriptiveStatistics(hv_nsga32_list);
//                results[3] = da.getMean();
//                da = new DescriptiveStatistics(hv_nsga33_list);
//                results[4] = da.getMean();
//                da = new DescriptiveStatistics(igd_nsga2_list);
//                results[5] = da.getMean();
//                da = new DescriptiveStatistics(igd_nsga3_list);
//                results[6] = da.getMean();
//                da = new DescriptiveStatistics(igd_nsga31_list);
//                results[7] = da.getMean();
//                da = new DescriptiveStatistics(igd_nsga32_list);
//                results[8] = da.getMean();
//                da = new DescriptiveStatistics(igd_nsga33_list);
//                results[9] = da.getMean();
//
//                da = new DescriptiveStatistics(hv_nsga2_list);
//                results[6] = da.getMin();
//                da = new DescriptiveStatistics(hv_nsga3_list);
//                results[7] = da.getMin();
//                da = new DescriptiveStatistics(hv_ansga_list);
//                results[8] = da.getMin();
//                da = new DescriptiveStatistics(igd_nsga2_list);
//                results[9] = da.getMin();
//                da = new DescriptiveStatistics(igd_nsga3_list);
//                results[10] = da.getMin();
//                da = new DescriptiveStatistics(igd_ansga_list);
//                results[11] = da.getMin();
//
//                da = new DescriptiveStatistics(hv_nsga2_list);
//                results[12] = da.getMax();
//                da = new DescriptiveStatistics(hv_nsga3_list);
//                results[13] = da.getMax();
//                da = new DescriptiveStatistics(hv_ansga_list);
//                results[14] = da.getMax();
//                da = new DescriptiveStatistics(igd_nsga2_list);
//                results[15] = da.getMax();
//                da = new DescriptiveStatistics(igd_nsga3_list);
//                results[16] = da.getMax();
//                da = new DescriptiveStatistics(igd_ansga_list);
//                results[17] = da.getMax();
//
//                da = new DescriptiveStatistics(hv_nsga2_list);
//                results[18] = da.getPercentile(75) - da.getPercentile(25);
//                da = new DescriptiveStatistics(hv_nsga3_list);
//                results[19] = da.getPercentile(75) - da.getPercentile(25);
//                da = new DescriptiveStatistics(hv_ansga_list);
//                results[20] = da.getPercentile(75) - da.getPercentile(25);
//                da = new DescriptiveStatistics(igd_nsga2_list);
//                results[21] = da.getPercentile(75) - da.getPercentile(25);
//                da = new DescriptiveStatistics(igd_nsga3_list);
//                results[22] = da.getPercentile(75) - da.getPercentile(25);
//                da = new DescriptiveStatistics(igd_ansga_list);
//                results[23] = da.getPercentile(75) - da.getPercentile(25);

                list.add(results);
                JMetalLogger.logger.info("max_Gen: " + max_generation[max_gen] + " => " + (iter + 1) + " / " + problemWithParetoFrontRef.length + " (" + Math.round((iter + 1.0) / problemWithParetoFrontRef.length * 100) + "%)");
                for (Double d : results) {
                    System.out.println(d);
                }

            }
        }

        aaExcel excel = new aaExcel(list);
        excel.toExcel();
    }

}