package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.selectionAlgorithms.*;

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

    public static SelectionAlgorithm getSelectionAlgorithm(final SelectionMethod method, final Parser parser) {
        if(method.equals(ELITE)) {
            return new Elite(parser);
        } else if(method.equals(ROULETTE)) {
            return new Roulette(parser);
        } else if(method.equals(UNIVERSAL)) {
            return new Universal(parser);
        } else if(method.equals(BOLTZMAN_ROULETTE)) {
            return new Boltzmann(parser);
        } else if(method.equals(TOURNAMENT_DETERINISTIC)) {
            return new TournamentDeterministic(parser);
        } else if(method.equals(TOURNAMENT_PROBABILISTIC)) {
            return new TournamentProbabilistic();
        } else if(method.equals(RANKING)) {
            //return new Ranking(parser);
        }
        return null;
    }
}
