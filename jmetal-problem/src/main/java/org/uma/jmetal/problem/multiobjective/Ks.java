package org.uma.jmetal.problem.multiobjective;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.JMetalException;

import java.util.BitSet;

public class Ks extends AbstractBinaryProblem implements ConstrainedProblem<BinarySolution> {
    private int bits;
    private int[] w; // weight
    private int[] k; // profit
    private int W; // weight capacity


    /** Constructor */
    public Ks() throws JMetalException {
        this(500);
    }

    /** Constructor */
    public Ks(Integer numberOfBits) throws JMetalException {
        setNumberOfVariables(1);
        setNumberOfObjectives(3);
        setNumberOfConstraints(1);
        setName("Ks");

        bits = numberOfBits;

        w = new int[bits];
        k = new int[bits];
        W = 0;

        for (int i = 0; i <bits; i++) {
            w[i] = (int) (Math.random() * 91) + 10;
            k[i] = (int) (Math.random() * 3) + 1;
            W += w[i];
        }
        W /= 2;
    }

    @Override
    protected int getBitsPerVariable(int index) {
        if (index != 0) {
            throw new JMetalException("Problem OneZeroMax has only a variable. Index = " + index) ;
        }
        return bits ;
    }

    @Override
    public BinarySolution createSolution() {
        return new DefaultBinarySolution(this) ;
    }

    /** Evaluate() method */
    @Override
    public void evaluate(BinarySolution solution) {
        int sumW = 0;
        int sumK = 0;

        int counterOnes;
        int counterZeroes;

        counterOnes = 0;
        counterZeroes = 0;

        BitSet bitset = solution.getVariableValue(0) ;

        for (int i = 0; i < bitset.length(); i++) {
            if (bitset.get(i)) {
                sumW += w[i];
                sumK += k[i];
            } else {
                counterZeroes++;
            }
        }

        // OneZeroMax is a maximization problem: multiply by -1 to minimize
        solution.setObjective(0, -1.0 * counterOnes);
        solution.setObjective(1, -1.0 * counterZeroes);
        solution.setObjective(2, -1.0 * counterZeroes);
    }

    @Override
    public void evaluateConstraints(BinarySolution solution) {
        BitSet[] constraint = new BitSet[this.getNumberOfConstraints()];
        BitSet x1 = solution.getVariableValue(0);/

        int totalW = 0;
        for (int i = 0; i < x1.length(); i++) {
            if(constraint[0].get(i))
                 W += w[i];
        }
        constraint[0] = W;

    }

}

