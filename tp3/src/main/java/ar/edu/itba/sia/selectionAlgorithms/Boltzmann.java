package ar.edu.itba.sia.selectionAlgorithms;

import ar.edu.itba.sia.structures.Candidate;

import java.util.LinkedList;
import java.util.List;

public class Boltzmann {

    private static final int K_START = 100;
    private static final double T_START = 20;

    private static final int K_DECREASE_FACTOR = 2;
    private static final double T_DECREASE_FACTOR = 1.5;

    private int k = K_START;
    private double t = T_START;

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

        k /= K_DECREASE_FACTOR;
        t /= T_DECREASE_FACTOR;

        return selected;
    }
}
