package ar.edu.itba.sia.model;

import ar.edu.itba.sia.model.character.Character;

import java.util.Comparator;

public class CharacterComparator {

    private static final Comparator<Character> naturalOrder = (c1, c2) -> {
        if (c1.getFitness() > c2.getFitness())
            return 1;
        else if (c1.getFitness() < c2.getFitness())
            return -1;
        else
            return 0;
    };

    private static final Comparator<Character> reverseOrder = (c1, c2) -> {
        if (c1.getFitness() > c2.getFitness())
            return -1;
        else if (c1.getFitness() < c2.getFitness())
            return 1;
        else
            return 0;
    };

    public static Comparator<Character> getNaturalOrder() { return naturalOrder; }

    public static Comparator<Character> getReverseOrder() { return reverseOrder; }
}
