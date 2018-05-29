package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.CharacterComparator;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentDeterministic implements SelectionAlgorithm {

    private int selectionCant;

    private int competitors;

    private final RandomSeeded r;

    public TournamentDeterministic(int selectionCant, int competitors, RandomSeeded r) {
        this.r = r;
        this.selectionCant = selectionCant;
        this.competitors = competitors;
    }

    public List<Character> select (List<Character> characters) {

        List<Character> optimalCharacters = new LinkedList<>();

        int k = 0;
        while (k < selectionCant) {

            List<Character> selected = new LinkedList<>();
            for (int i = 0; i < competitors; i++) {
                int random = r.nextInt(0, characters.size());
                selected.add(characters.get(random));
            }

            selected.sort(CharacterComparator.getNaturalOrder());
            optimalCharacters.add(selected.get(selected.size() - 1));
            k++;
        }
        return optimalCharacters;
    }
}
