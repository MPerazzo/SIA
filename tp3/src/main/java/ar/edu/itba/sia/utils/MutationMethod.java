package ar.edu.itba.sia.utils;

import javax.management.AttributeNotFoundException;

public enum MutationMethod {
    UNIFORM, NO_UNIFORM;

    public static MutationMethod getMutationMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(UNIFORM.toString())) {
            return UNIFORM;
        } else if(method.equals(NO_UNIFORM.toString())) {
            return NO_UNIFORM;
        } else throw new AttributeNotFoundException("mutation method doesn\'t exist");
    }
}
