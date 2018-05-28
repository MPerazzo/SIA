package ar.edu.itba.sia.utils.enums;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.selectionAlgorithms.*;
import ar.edu.itba.sia.utils.ConfigurationManager;

import javax.management.AttributeNotFoundException;

public enum SelectionMethod {
    ELITE, ROULETTE, UNIVERSAL, BOLTZMANN_ROULETTE, TOURNAMENT_DETERMINISTIC,
    TOURNAMENT_PROBABILISTIC, RANKING;

    public static SelectionMethod getSelectionMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(ELITE.toString())) {
            return ELITE;
        } else if(method.equals(ROULETTE.toString())) {
            return ROULETTE;
        } else if(method.equals(UNIVERSAL.toString())) {
            return UNIVERSAL;
        } else if(method.equals(BOLTZMANN_ROULETTE.toString())) {
            return BOLTZMANN_ROULETTE;
        } else if(method.equals(TOURNAMENT_DETERMINISTIC.toString())) {
            return TOURNAMENT_DETERMINISTIC;
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
            return new Roulette(selectionCount, m.getRandomSeeded());
        } else if(method.equals(UNIVERSAL)) {
            return new Universal(selectionCount, m.getRandomSeeded());
        } else if(method.equals(BOLTZMANN_ROULETTE)) {
            return new Boltzmann(selectionCount, m.getTemp(), m.getExponentialFactor(), m.getRandomSeeded());
        } else if(method.equals(TOURNAMENT_DETERMINISTIC)) {
            return new TournamentDeterministic(selectionCount, m.getTournamentCantCompetitors(), m.getRandomSeeded());
        } else if(method.equals(TOURNAMENT_PROBABILISTIC)) {
            return new TournamentProbabilistic(selectionCount, m.getTournamentProb(), m.getRandomSeeded());
        } else if(method.equals(RANKING)) {
            return new Ranking(selectionCount, m.getRandomSeeded());
        }
        return null;
    }
}
