package ar.edu.itba.sia.utils.enums;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.mutationAlgorithms.SingleGenMutation;
import ar.edu.itba.sia.mutationAlgorithms.MultiGenMutation;
import ar.edu.itba.sia.utils.ConfigurationManager;

import javax.management.AttributeNotFoundException;

public enum MutationMethod {
    SINGLE_GEN_MUTATION, MULTI_GEN_MUTATION;

    public static MutationMethod getMutationMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(SINGLE_GEN_MUTATION.toString())) {
            return SINGLE_GEN_MUTATION;
        } else if(method.equals(MULTI_GEN_MUTATION.toString())) {
            return MULTI_GEN_MUTATION;
        } else throw new AttributeNotFoundException("mutation method doesn\'t exist");
    }

    public static MutationAlgorithm getMutationAlgorithm(final MutationMethod method, ConfigurationManager m) {
        if (method.equals(SINGLE_GEN_MUTATION))
            return new SingleGenMutation(m);
        else
            return new MultiGenMutation(m);
    }
}
