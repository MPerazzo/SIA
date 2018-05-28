package ar.edu.itba.sia.utils.enums;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.selectionAlgorithms.*;
import ar.edu.itba.sia.utils.ConfigurationManager;

import javax.management.AttributeNotFoundException;

public enum SelectionMethod {
    ELITE, ROULETTE, UNIVERSAL, BOLTZMANN_ROULETTE, TOURNAMENT_DET,
    TOURNAMENT_PROB, RANKING;

    public static SelectionMethod getSelectionMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(ELITE.toString())) {
            return ELITE;
        } else if(method.equals(ROULETTE.toString())) {
            return ROULETTE;
        } else if(method.equals(UNIVERSAL.toString())) {
            return UNIVERSAL;
        } else if(method.equals(BOLTZMANN_ROULETTE.toString())) {
            return BOLTZMANN_ROULETTE;
        } else if(method.equals(TOURNAMENT_DET.toString())) {
            return TOURNAMENT_DET;
        } else if(method.equals(TOURNAMENT_PROB.toString())) {
            return TOURNAMENT_PROB;
        } else if(method.equals(RANKING.toString())) {
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
        } else if(method.equals(TOURNAMENT_DET)) {
            return new TournamentDeterministic(selectionCount, m.getTournamentCantCompetitors(), m.getRandomSeeded());
        } else if(method.equals(TOURNAMENT_PROB)) {
            return new TournamentProbabilistic(selectionCount, m.getTournamentProb(), m.getRandomSeeded());
        } else if(method.equals(RANKING)) {
            return new Ranking(selectionCount, m.getRandomSeeded());
        }
        return null;
    }
}
