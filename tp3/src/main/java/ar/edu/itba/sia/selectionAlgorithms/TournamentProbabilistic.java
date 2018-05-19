package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentProbabilistic {

    private static final int K = 20;

    private void select (List<Candidate> candidates) {

        List<Candidate> selected = new LinkedList<>();

        int k = K;
        while (k-- != 0) {
            int random1 = ThreadLocalRandom.current().nextInt(0, candidates.size());
            int random2 = ThreadLocalRandom.current().nextInt(0, candidates.size());

            List<Candidate> duo = new LinkedList<>();
            duo.add(candidates.get(random1));
            duo.add(candidates.get(random2));
            duo.sort((c1, c2) -> {
                if (c1.getFitness() > c2.getFitness())
                    return 1;
                else if (c1.getFitness() < c2.getFitness())
                    return -1;
                else
                    return 0;
            });

            double random = Math.random();

            if (random < 0.75)
                selected.add(candidates.get(1));
            else
                selected.add(candidates.get(0));
        }
    }
}
