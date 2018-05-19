package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Roulette {

    private int k;

    public Roulette(Parser parser) {
        k = parser.getSelectionCant();
    }

    private List<Candidate> select(List<Candidate> candidates) {

        List<Candidate> selected = new LinkedList<>();
        double accumToMatch[] = new double[k];

        for (int i = 0 ; i < k ; i++) {
            double random = Math.random();
            accumToMatch[i] = random;
        }

        double totalFitness = 0;
        for (Candidate c : candidates)
            totalFitness += c.getFitness();

        double prevCandidateAccum = 0;
        for (int i = 0, j=0 ; i < k && j < accumToMatch.length ;) {
            Candidate currentCandidate = candidates.get(i);
            double currentCandidateAccum = prevCandidateAccum + (currentCandidate.getFitness() / totalFitness);
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
