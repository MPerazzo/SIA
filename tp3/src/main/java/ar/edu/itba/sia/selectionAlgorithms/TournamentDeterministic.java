package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentDeterministic {

    private static final int K = 20;

    private int m;

    public TournamentDeterministic(Parser parser) {
        m = parser.getTournamentCantCompetitors();
    }

    private List<Candidate> select (List<Candidate> candidates) {

        List<Candidate> optimalCandidates = new LinkedList<>();

        int k = K;
        while (k-- != 0) {

            List<Candidate> selected = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                int random = ThreadLocalRandom.current().nextInt(0, candidates.size());
                selected.add(candidates.get(random));
            }

            selected.sort((c1, c2) -> {
                if (c1.getFitness() > c2.getFitness())
                    return 1;
                else if (c1.getFitness() < c2.getFitness())
                    return -1;
                else
                    return 0;
            });
            optimalCandidates.add(selected.get(selected.size() - 1));
        }
        return optimalCandidates;
    }
}
