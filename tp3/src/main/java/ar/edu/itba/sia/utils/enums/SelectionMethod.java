package ar.edu.itba.sia.utils.enums;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.selectionAlgorithms.*;
import ar.edu.itba.sia.utils.ConfigurationManager;

import javax.management.AttributeNotFoundException;

public enum SelectionMethod {
    ELITE, ROULETTE, UNIVERSAL, BOLTZMAN_ROULETTE, TOURNAMENT_DETERINISTIC,
    TOURNAMENT_PROBABILISTIC, RANKING;

    public static SelectionMethod getSelectionMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(ELITE.toString())) {
            return ELITE;
        } else if(method.equals(ROULETTE.toString())) {
            return ROULETTE;
        } else if(method.equals(UNIVERSAL.toString())) {
            return UNIVERSAL;
        } else if(method.equals(BOLTZMAN_ROULETTE.toString())) {
            return BOLTZMAN_ROULETTE;
        } else if(method.equals(TOURNAMENT_DETERINISTIC.toString())) {
            return TOURNAMENT_DETERINISTIC;
        } else if(method.equals(TOURNAMENT_PROBABILISTIC.toString())) {
            return TOURNAMENT_PROBABILISTIC;
        } else if(method.equals(RANKING)) {
            return RANKING;
        } else throw new AttributeNotFoundException("selection method doesn\'t exist");
    }

    public static SelectionAlgorithm getSelectionAlgorithm(final SelectionMethod method,
                                                           final int selectionCount,
                                                           final ConfigurationManager m) {
        if(method.equals(ELITE)) {
            return new Elite(selectionCount);
        } else if(method.equals(ROULETTE)) {
            return new Roulette(selectionCount);
        } else if(method.equals(UNIVERSAL)) {
            return new Universal(selectionCount);
        } else if(method.equals(BOLTZMAN_ROULETTE)) {
            return new Boltzmann(selectionCount, m.getTemp(), m.getExponentialFactor());
        } else if(method.equals(TOURNAMENT_DETERINISTIC)) {
            return new TournamentDeterministic(selectionCount, m.getTournamentCantCompetitors());
        } else if(method.equals(TOURNAMENT_PROBABILISTIC)) {
            return new TournamentProbabilistic(selectionCount, m.getTournamentProb());
        } else if(method.equals(RANKING)) {
            return new Ranking(selectionCount);
        }
        return null;
    }
}
