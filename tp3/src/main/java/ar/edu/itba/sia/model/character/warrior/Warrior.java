package ar.edu.itba.sia.model.character.warrior;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

public abstract class Warrior extends Character {

    public Warrior(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                   final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);
    }

    @Override
    public double getAttackFactor() {
        return 0.6;
    }

    @Override
    public double getDefenseFactor() {
        return 0.4;
    }
}
