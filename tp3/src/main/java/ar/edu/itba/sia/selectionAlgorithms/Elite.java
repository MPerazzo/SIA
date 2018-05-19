package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;

import java.util.LinkedList;
import java.util.List;

public class Elite {

    private static final int K_START = 100;

    private static final int K_DECREASE_FACTOR = 2;

    private int k = K_START;

    private List<Candidate> select(List<Candidate> candidates) {

        List<Candidate> selected = new LinkedList<>();

        candidates.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        });

        for (int i = 0; i < k; i++)
            selected.add(candidates.get(i));

        k /= K_DECREASE_FACTOR;

        return selected;
    }
}
