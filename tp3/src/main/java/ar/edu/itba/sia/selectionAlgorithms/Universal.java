package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Universal {

    private int k;

    public Universal(Parser parser) {
        k = parser.getSelectionCant();
    }

    private List<Candidate> select(List<Candidate> candidates) {

        List<Candidate> selected = new LinkedList<>();
        double accumToMatch[] = new double[k];

        double random = Math.random();

        for (int i = 0 ; i < k ; i++)
            accumToMatch[i] = (random + i)/k;

        double totalFitness = 0;
        for (Candidate c : candidates)
            totalFitness += c.getFitness();

        double prevCandidateAccum = candidates.get(0).getFitness() / totalFitness;
        for (int i = 1, j=0 ; i < k ; i++) {
            Candidate currentCandidate = candidates.get(i);
            double currentCandidateAccum = prevCandidateAccum + (currentCandidate.getFitness() / totalFitness);
            double currentAccumToMatch = accumToMatch[j];

            if (prevCandidateAccum < currentAccumToMatch && currentAccumToMatch < currentCandidateAccum) {
                selected.add(currentCandidate);
                j++;
            }
            prevCandidateAccum = currentCandidateAccum;
        }

        return selected;
    }
}
