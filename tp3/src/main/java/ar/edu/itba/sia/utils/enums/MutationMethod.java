package ar.edu.itba.sia.utils.enums;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.mutationAlgorithms.GenMutation;
import ar.edu.itba.sia.mutationAlgorithms.MultiGenMutation;
import ar.edu.itba.sia.utils.ConfigurationManager;

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

    public static MutationAlgorithm getMutationAlgorithm(final MutationMethod method, ConfigurationManager m) {
        if (method.equals(UNIFORM))
            return new GenMutation(m);
        else
            return new MultiGenMutation(m);
    }
}
