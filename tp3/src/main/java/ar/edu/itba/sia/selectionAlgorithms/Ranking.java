package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.CharacterComparator;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ranking implements SelectionAlgorithm {

    private int selectionCount;
    private final RandomSeeded r;

    public Ranking(int selectionCant, RandomSeeded r) {
        selectionCount = selectionCant;
        this.r = r;
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[selectionCount];

        for (int i = 0 ; i < selectionCount; i++) {
            double random = r.nextDouble();
            accumToMatch[i] = random;
        }

        characters.sort(CharacterComparator.getNaturalOrder());

        double totalFitness = characters.size()*(characters.size()+1)/2;

        double prevCharacterAccum = 0;
        for (int i = 0, j=0 ; j < accumToMatch.length ;) {
            Character currentCharacter = characters.get(i);
            double currentCharacterAccum = prevCharacterAccum + ((i + 1) / totalFitness);
            double currentAccumToMatch = accumToMatch[j];

            if (prevCharacterAccum <= currentAccumToMatch && currentAccumToMatch <= currentCharacterAccum) {
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

        return selected;
    }
}
