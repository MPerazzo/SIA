package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;

public class Boltzmann implements SelectionAlgorithm {

    private final int selectionCant;
    private final double t0;
    private final double exponentialFactor;
    private double t;

    private final long runningTimeStart = System.currentTimeMillis();

    public Boltzmann(int selectionCant, double t0, double exponentialFactor) {
        this.selectionCant = selectionCant;
        this.t0 = t0;
        this.exponentialFactor = exponentialFactor;
        t = t0;
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[selectionCant];

        for (int i = 0 ; i < selectionCant; i++) {
            double random = Math.random();
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

            if (prevCharacterAccum < currentAccumToMatch && currentAccumToMatch < currentCharacterAccum) {
                selected.add(currentCharacter);
                j++;
                i = 0;
                prevCharacterAccum = 0;
            }
            else {
                prevCharacterAccum = currentCharacterAccum;
                i++;
            }
        }
        long runningTimeEnd = System.currentTimeMillis();
        double runningTime = ((double)(runningTimeEnd - runningTimeStart)) / 1000;

        t = t0 * Math.exp(-runningTime * exponentialFactor);

        return selected;
    }
}
