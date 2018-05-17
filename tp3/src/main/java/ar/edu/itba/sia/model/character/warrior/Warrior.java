package ar.edu.itba.sia.model.character.warrior;

import ar.edu.itba.sia.model.character.*;

public abstract class Warrior extends ar.edu.itba.sia.model.character.Character {

    @Override
    public double getAttackFactor() {
        return 0.6;
    }

    @Override
    public double getDefenseFactor() {
        return 0.4;
    }
}
