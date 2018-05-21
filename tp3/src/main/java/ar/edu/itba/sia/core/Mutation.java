package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.List;

public class Mutation {

    public static void mutate(List<Character> newGen, MutationAlgorithm mutationAlgorithm, double mutateProbability) {
        for (Character c : newGen) {
            double random = Math.random();
            if (random < mutateProbability) {
                mutationAlgorithm.mutate(c);
            }
        }
    }
}
