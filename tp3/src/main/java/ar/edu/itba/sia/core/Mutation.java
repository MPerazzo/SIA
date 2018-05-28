package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mutation {

    public static void mutate(List<Character> newGen, MutationAlgorithm mutationAlgorithm,
                              double mutateProbability, RandomSeeded r) {
        for (Character c : newGen) {
            double random = r.nextDouble();
            if (random < mutateProbability) {
                mutationAlgorithm.mutate(c);
            }
        }
    }
}
