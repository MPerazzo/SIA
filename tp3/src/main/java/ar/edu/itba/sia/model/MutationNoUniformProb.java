package ar.edu.itba.sia.model;

import ar.edu.itba.sia.interfaces.MutationProbCalculator;

public class MutationNoUniformProb implements MutationProbCalculator {

    private final static double MIN_PROB = 0.1;

    private double probability;
    private double decreasePercent;

    public MutationNoUniformProb(double initialProb, double decreasePercent) {
        this.probability = initialProb;
        this.decreasePercent = decreasePercent;

    }

    @Override
    public double getProb() {
        if (probability > MIN_PROB) {
            probability *= (1 - decreasePercent);

            if (probability < MIN_PROB)
                probability = MIN_PROB;
        }

        return probability;
    }
}
