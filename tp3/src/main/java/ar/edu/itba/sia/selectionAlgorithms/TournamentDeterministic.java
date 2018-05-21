package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentDeterministic implements SelectionAlgorithm {

    private int selectionCant;

    private int competitors;

    public TournamentDeterministic(int selectionCant, int competitors) {
        this.selectionCant = selectionCant;
        this.competitors = competitors;
    }

    public List<Character> select (List<Character> characters) {

        List<Character> optimalCharacters = new LinkedList<>();

        int k = selectionCant;
        while (k-- != 0) {

            List<Character> selected = new LinkedList<>();
            for (int i = 0; i < competitors; i++) {
                int random = ThreadLocalRandom.current().nextInt(0, characters.size());
                selected.add(characters.get(random));
            }

            selected.sort((c1, c2) -> {
                if (c1.getFitness() > c2.getFitness())
                    return 1;
                else if (c1.getFitness() < c2.getFitness())
                    return -1;
                else
                    return 0;
            });
            optimalCharacters.add(selected.get(selected.size() - 1));
        }
        return optimalCharacters;
    }
}
