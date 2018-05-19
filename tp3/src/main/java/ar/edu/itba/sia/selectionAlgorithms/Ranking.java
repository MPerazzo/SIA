package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;

import java.util.LinkedList;
import java.util.List;

public class Ranking {

    private static final int K_START = 100;

    private static final int K_DECREASE_FACTOR = 2;

    private int k = K_START;

    private List<Candidate> select(List<Candidate> candidates) {

        List<Candidate> selected = new LinkedList<>();
        double accumToMatch[] = new double[k];

        for (int i = 0 ; i < k ; i++) {
            double random = Math.random();
            accumToMatch[i] = random;
        }

        candidates.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        });

        int totalFitness = 0;
        for (int i = 1 ; i < candidates.size() ; i++)
            totalFitness += i;

        double prevCandidateAccum = 1 / totalFitness;
        for (int i = 1, j=0 ; i < k ; i++) {
            Candidate currentCandidate = candidates.get(i);
            double currentCandidateAccum = prevCandidateAccum + (i / totalFitness);
            double currentAccumToMatch = accumToMatch[j];

            if (prevCandidateAccum < currentAccumToMatch && currentAccumToMatch < currentCandidateAccum) {
                selected.add(currentCandidate);
                j++;
            }
            prevCandidateAccum = currentCandidateAccum;
        }

        k /= K_DECREASE_FACTOR;

        return selected;
    }
}
