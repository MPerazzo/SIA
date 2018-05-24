package ar.edu.itba.sia.model;

import ar.edu.itba.sia.interfaces.MutationProbCalculator;

public class MutationNoUniformProb implements MutationProbCalculator {

    private final static double PROB_INCREASE = 0.05;

    private double probability;

    public MutationNoUniformProb(double initialProb) {
        this.probability = initialProb;
    }

    @Override
    public double getProb() {
        if (probability < 1)
            probability += PROB_INCREASE;

        return probability;
    }
}
