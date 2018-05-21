package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.replacementAlgorithms.First;
import ar.edu.itba.sia.replacementAlgorithms.Second;
import ar.edu.itba.sia.replacementAlgorithms.Third;

import javax.management.AttributeNotFoundException;

public enum ReplacementMethod {
    FIRST, SECOND, THIRD;

    public static ReplacementMethod getReplacementMethod(final String method) throws AttributeNotFoundException {
        if(method.equals(FIRST.toString())) {
            return FIRST;
        } else if(method.equals(SECOND.toString())) {
            return SECOND;
        } else if(method.equals(THIRD.toString())) {
            return THIRD;
        } else throw new AttributeNotFoundException("replacement method doesn\'t exist");
    }

    public static ReplacementAlgorithm getReplacementAlgorithm(final ReplacementMethod method) {
        if(method.equals(FIRST)) {
            return new First();
        } else if(method.equals(SECOND)) {
            return new Second();
        } else {
            return new Third();
        }
    }
}
