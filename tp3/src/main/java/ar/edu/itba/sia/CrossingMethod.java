package ar.edu.itba.sia;

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
}
