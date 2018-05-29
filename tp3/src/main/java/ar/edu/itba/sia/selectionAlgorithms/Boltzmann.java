package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;

public class Boltzmann implements SelectionAlgorithm {

    private static final double TEMP_LIMIT = 0.04;
    private final int selectionCant;
    private final double t0;
    private final double exponentialFactor;
    private double t;

    private double counter = 1;

    private final RandomSeeded r;

    public Boltzmann(int selectionCant, double t0, double exponentialFactor, RandomSeeded r) {
        this.selectionCant = selectionCant;
        this.t0 = t0;
        this.exponentialFactor = exponentialFactor;
        t = t0;

        this.r = r;
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[selectionCant];

        for (int i = 0 ; i < selectionCant; i++) {
            double random = r.nextDouble();
            accumToMatch[i] = random;
        }

        double totalExpVal = 0;
        for (Character c : characters)
            totalExpVal += Math.exp(c.getFitness() / t);

        double prevCharacterAccum = 0;
        for (int i = 0, j=0 ; j < accumToMatch.length ;) {
            Character currentCharacter = characters.get(i);
            double currentCharacterAccum = prevCharacterAccum +
                    (Math.exp(currentCharacter.getFitness() / t) / totalExpVal);
            double currentAccumToMatch = accumToMatch[j];

            if (prevCharacterAccum <= currentAccumToMatch && currentAccumToMatch <= currentCharacterAccum) {
                selected.add(currentCharacter);
                j++;
                i = 0;
                prevCharacterAccum = 0;
            } else {
                prevCharacterAccum = currentCharacterAccum;
                i++;
            }
        }

        if (t > TEMP_LIMIT) {
            t = t0 * Math.exp(-counter++ * exponentialFactor);

            if (t < TEMP_LIMIT)
                t = TEMP_LIMIT;
        }

        return selected;
    }
}
