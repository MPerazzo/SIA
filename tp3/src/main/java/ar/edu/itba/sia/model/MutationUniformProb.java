package ar.edu.itba.sia.model;

import ar.edu.itba.sia.interfaces.MutationProbCalculator;

public class MutationUniformProb implements MutationProbCalculator {

    private final double probability;

    public MutationUniformProb(double probability) {
        this.probability = probability;
    }

    @Override
    public double getProb() {
        return probability;
    }
}
