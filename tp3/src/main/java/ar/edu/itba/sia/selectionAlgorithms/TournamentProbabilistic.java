package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentProbabilistic implements SelectionAlgorithm {

    private final int selectionCant;
    private final double p;
    private final RandomSeeded r;

    public TournamentProbabilistic(final int selectionCant, final double p, RandomSeeded r) {
        this.selectionCant = selectionCant;
        this.p = p;
        this.r = r;
    }

    public List<Character> select (List<Character> characters) {

        List<Character> selected = new LinkedList<>();

        int k = 0;
        while (k < selectionCant) {
            int random1 = r.nextInt(0, characters.size());
            int random2 = r.nextInt(0, characters.size());

            List<Character> duo = new LinkedList<>();
            duo.add(characters.get(random1));
            duo.add(characters.get(random2));
            duo.sort((c1, c2) -> {
                if (c1.getFitness() > c2.getFitness())
                    return 1;
                else if (c1.getFitness() < c2.getFitness())
                    return -1;
                else
                    return 0;
            });

            double random = r.nextDouble();

            if (random < p)
                selected.add(characters.get(1));
            else
                selected.add(characters.get(0));

            k++;
        }
        return selected;
    }
}
