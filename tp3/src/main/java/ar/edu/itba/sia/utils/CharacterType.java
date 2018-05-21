package ar.edu.itba.sia.utils;

import javax.management.AttributeNotFoundException;

public enum CharacterType {
    ARCHER1, ARCHER2, ARCHER3, ASSASIN1, ASSASIN2, ASSASIN3, DEFENDER1, DEFENDER2, DEFENDER3,
    WARRIOR1, WARRIOR2, WARRIOR3;

    public static CharacterType getCharacterType(String type) throws AttributeNotFoundException {
        if(type.equals(ARCHER1.toString())) {
            return ARCHER1;
        } else if(type.equals(ARCHER2.toString())) {
            return ARCHER2;
        } else if(type.equals(ARCHER3.toString())) {
            return ARCHER3;
        } else if(type.equals(ASSASIN1.toString())) {
            return ASSASIN1;
        } else if(type.equals(ASSASIN2.toString())) {
            return ASSASIN2;
        } else if(type.equals(ASSASIN3.toString())) {
            return ASSASIN3;
        } else if(type.equals(DEFENDER1.toString())) {
            return DEFENDER1;
        } else if(type.equals(DEFENDER2.toString())) {
            return DEFENDER2;
        } else if(type.equals(DEFENDER3.toString())) {
            return DEFENDER3;
        } else if(type.equals(WARRIOR1.toString())) {
            return WARRIOR1;
        } else if(type.equals(WARRIOR2.toString())) {
            return WARRIOR2;
        } else if(type.equals(WARRIOR3.toString())) {
            return WARRIOR3;
        } else throw new AttributeNotFoundException("character type doesn\'t exist");
    }


}
