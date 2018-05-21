package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;

public class Universal implements SelectionAlgorithm {

    private int selectionCant;

    public Universal(int selectionCant) {
        this.selectionCant = selectionCant;
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[selectionCant];

        double random = Math.random();

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

            if (prevCharacterAccum < currentAccumToMatch && currentAccumToMatch < currentCharacterAccum) {
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
