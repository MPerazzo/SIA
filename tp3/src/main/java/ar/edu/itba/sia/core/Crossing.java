package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Crossing {

    public static List<Character> randomCross(LinkedList<Character> selected, CrossAlgorithm crossAlgorithm,
                                              double crossProbability) {

        List<Character> newGen = new LinkedList<>();

        Collections.shuffle(selected);

        int size = selected.size();

        for (int i = 0; i < size ; ) {

            Character c1 = selected.pop();
            Character c2 = selected.pop();

            double random = Math.random();

            if (random < crossProbability)
                crossAlgorithm.cross(newGen, c1, c2);
            else {
                newGen.add(c1);
                newGen.add(c2);
            }

            i += 2;
        }
        if (!selected.isEmpty()) {
            newGen.add(selected.pop());
        }
        return newGen;
    }
}
