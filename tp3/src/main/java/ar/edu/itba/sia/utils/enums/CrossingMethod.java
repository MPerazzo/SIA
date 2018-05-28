package ar.edu.itba.sia.utils.enums;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.crossAlgorithms.AnnularCross;
import ar.edu.itba.sia.crossAlgorithms.SinglePointCross;
import ar.edu.itba.sia.crossAlgorithms.TwoPointCross;
import ar.edu.itba.sia.crossAlgorithms.UniformCross;
import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.utils.ConfigurationManager;

import javax.management.AttributeNotFoundException;

public enum CrossingMethod {
    ONE_POINT, TWO_POINT, UNIFORM, ANNULAR;

    public static CrossingMethod getCrossingMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(ONE_POINT.toString())) {
            return ONE_POINT;
        } else if(method.equals(TWO_POINT.toString())) {
            return TWO_POINT;
        } else if(method.equals(UNIFORM.toString())) {
            return UNIFORM;
        } else if(method.equals(ANNULAR.toString())) {
            return ANNULAR;
        } else throw new AttributeNotFoundException("crossing method doesn\'t exist");
    }

    public static CrossAlgorithm getCrossingAlgorithm(final CrossingMethod method, RandomSeeded r) {

        if (method.equals(ONE_POINT)) {
            return new SinglePointCross(r);
        } else if (method.equals(TWO_POINT)) {
            return new TwoPointCross(r);
        } else if (method.equals(UNIFORM)) {
            return new UniformCross(r);
        } else
            return new AnnularCross(r);
    }
}
