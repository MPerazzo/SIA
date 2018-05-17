package ar.edu.itba.sia.model.character.defender;

import ar.edu.itba.sia.model.character.*;

public abstract class Defender extends ar.edu.itba.sia.model.character.Character {

    @Override
    public double getAttackFactor() {
        return 0.9;
    }

    @Override
    public double getDefenseFactor() {
        return 0.1;
    }
}
