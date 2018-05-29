package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.CharacterComparator;
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

        characters.sort(CharacterComparator.getReverseOrder());

        for (int i = 0; i < selectionCant; i++)
            selected.add(characters.get(i));

        return selected;
    }
}
