package ar.edu.itba.sia.structures;

import ar.edu.itba.sia.model.character.Character;

public class Candidate {
    private Character character;
    private double fitness;

    //patron builder
    public Candidate(Character character) {
        this.character = character;
        this.fitness = character.getFitness();
    }

    public double getFitness() {
        return fitness;
    }
}
