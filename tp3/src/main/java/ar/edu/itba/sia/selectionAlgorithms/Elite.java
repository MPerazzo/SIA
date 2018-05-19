package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Elite {

    private int k;

    public Elite(Parser parser) {
        k = parser.getSelectionCant();
    }

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

        return selected;
    }
}
