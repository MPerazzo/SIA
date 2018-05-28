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
                                              double crossProbability, RandomSeeded r) {

        List<Character> children = new LinkedList<>();

        Collections.shuffle(selected, r.getRandom());

        int size = selected.size();

        for (int i = 0; i < size ; ) {

            Character c1 = selected.pop();
            Character c2 = selected.pop();

            double random = r.nextDouble();

            if (random < crossProbability)
                crossAlgorithm.cross(children, c1, c2);
            else {
                children.add(c1);
                children.add(c2);
            }

            i += 2;
        }
        if (!selected.isEmpty()) {
            children.add(selected.pop());
        }
        return children;
    }
}
