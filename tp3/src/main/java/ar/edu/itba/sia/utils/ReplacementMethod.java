package ar.edu.itba.sia.utils;

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
}
