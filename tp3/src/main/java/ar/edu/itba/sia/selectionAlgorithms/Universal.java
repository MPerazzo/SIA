package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Universal implements SelectionAlgorithm {

    private int selectionCant;
    private final RandomSeeded r;

    public Universal(int selectionCant, RandomSeeded r) {
        this.selectionCant = selectionCant;
        this.r = r;
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[selectionCant];

        double random = r.nextDouble();

        for (int i = 0 ; i < selectionCant; i++)
            accumToMatch[i] = (random + i)/ selectionCant;

        double totalFitness = 0;
        for (Character c : characters)
            totalFitness += c.getFitness();

        double prevCharacterAccum = 0;
        for (int i = 0, j=0 ; j < accumToMatch.length ;) {
            Character currentCharacter = characters.get(i);
            double currentCharacterAccum = prevCharacterAccum + (currentCharacter.getFitness() / totalFitness);
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
        return selected;
    }
}
