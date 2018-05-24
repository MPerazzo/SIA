package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;

public class Elite implements SelectionAlgorithm {

    private int selectionCant;

    public Elite(int selectionCant) {
        this.selectionCant = selectionCant;
    }

    public List<Character> select(List<Character> characters) {

        List<Character> selected = new LinkedList<>();

        characters.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });

        for (int i = 0; i < selectionCant; i++)
            selected.add(characters.get(i));

        return selected;
    }
}
