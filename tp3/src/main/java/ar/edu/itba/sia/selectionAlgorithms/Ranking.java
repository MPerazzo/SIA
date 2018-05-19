package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Ranking {

    private int k;

    public Ranking(Parser parser) {
        k = parser.getSelectionCant();
    }

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
        for (int i = 0 ; i < candidates.size() ; i++)
            totalFitness += (i + 1);

        double prevCandidateAccum = 0;
        for (int i = 0, j=0 ; i < k && j < accumToMatch.length ;) {
            Candidate currentCandidate = candidates.get(i);
            double currentCandidateAccum = prevCandidateAccum + ((i + 1) / totalFitness);
            double currentAccumToMatch = accumToMatch[j];

            if (prevCandidateAccum < currentAccumToMatch && currentAccumToMatch < currentCandidateAccum) {
                selected.add(currentCandidate);
                j++;
                i = 0;
                prevCandidateAccum = 0;
            }
            else {
                prevCandidateAccum = currentCandidateAccum;
                i++;
            }
        }

        return selected;
    }
}
