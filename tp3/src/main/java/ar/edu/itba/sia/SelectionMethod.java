package ar.edu.itba.sia;

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
}
