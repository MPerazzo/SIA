package ar.edu.itba.sia.utils.enums;

import javax.management.AttributeNotFoundException;

public enum MutationType {

    UNIFORM, NO_UNIFORM;

    public static MutationType getMutationType(final String method) throws AttributeNotFoundException {
        if(method.equals(UNIFORM.toString())) {
            return UNIFORM;
        } else if(method.equals(NO_UNIFORM.toString())) {
            return NO_UNIFORM;
        } else throw new AttributeNotFoundException("mutation method doesn\'t exist");
    }
}
