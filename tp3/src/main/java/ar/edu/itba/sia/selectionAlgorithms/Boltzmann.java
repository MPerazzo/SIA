package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Boltzmann {

    private static final double T_DECREASE_FACTOR = 1.5;

    private int k;
    private double t;

    public Boltzmann(Parser parser) {
        k = parser.getSelectionCant();
        t = parser.getTemp();
    }

    private List<Candidate> select(List<Candidate> candidates) {

        List<Candidate> selected = new LinkedList<>();
        double accumToMatch[] = new double[k];

        for (int i = 0 ; i < k ; i++) {
            double random = Math.random();
            accumToMatch[i] = random;
        }

        double totalExpVal = 0;
        for (Candidate c : candidates)
            totalExpVal += Math.exp(c.getFitness() / t);

        double prevCandidateAccum = Math.exp(candidates.get(0).getFitness() / t) / totalExpVal;
        for (int i = 1, j=0 ; i < k ; i++) {
            Candidate currentCandidate = candidates.get(i);
            double currentCandidateAccum = prevCandidateAccum +
                    (Math.exp(currentCandidate.getFitness() / t) / totalExpVal);
            double currentAccumToMatch = accumToMatch[j];

            if (prevCandidateAccum < currentAccumToMatch && currentAccumToMatch < currentCandidateAccum) {
                selected.add(currentCandidate);
                j++;
            }
            prevCandidateAccum = currentCandidateAccum;
        }

        t /= T_DECREASE_FACTOR;

        return selected;
    }
}
