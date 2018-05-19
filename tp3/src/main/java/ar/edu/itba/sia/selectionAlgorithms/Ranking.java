package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Ranking {

    private int k;

    public Ranking(Parser parser) {
        k = parser.getSelectionCant();
    }

    private List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();
        double accumToMatch[] = new double[k];

        for (int i = 0 ; i < k ; i++) {
            double random = Math.random();
            accumToMatch[i] = random;
        }

        characters.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        });

        int totalFitness = 0;
        for (int i = 0 ; i < characters.size() ; i++)
            totalFitness += (i + 1);

        double prevCharacterAccum = 0;
        for (int i = 0, j=0 ; j < accumToMatch.length ;) {
            Character currentCharacter = characters.get(i);
            double currentCharacterAccum = prevCharacterAccum + ((i + 1) / totalFitness);
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

        return selected;
    }
}
