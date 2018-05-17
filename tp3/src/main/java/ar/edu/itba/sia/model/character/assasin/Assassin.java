package ar.edu.itba.sia.model.character.assasin;

import ar.edu.itba.sia.model.character.*;

public abstract class Assassin extends ar.edu.itba.sia.model.character.Character {

    @Override
    public double getAttackFactor() {
        return 0.7;
    }

    @Override
    public double getDefenseFactor() {
        return 0.3;
    }
}
