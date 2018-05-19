package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.interfaces.SelectionAlgortihm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Boltzmann implements SelectionAlgortihm {

    private static final double T_DECREASE_FACTOR = 1.5;

    private int k;
    private double t;

    public Boltzmann(Parser parser) {
        k = parser.getSelectionCant();
        t = parser.getTemp();
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[k];

        for (int i = 0 ; i < k ; i++) {
            double random = Math.random();
            accumToMatch[i] = random;
        }

        double totalExpVal = 0;
        for (Character c : characters)
            totalExpVal += Math.exp(c.getFitness() / t);

        double prevCharacterAccum = 0;
        for (int i = 0, j=0 ; i < k && j < accumToMatch.length ;) {
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

        t /= T_DECREASE_FACTOR;

        return selected;
    }
}
