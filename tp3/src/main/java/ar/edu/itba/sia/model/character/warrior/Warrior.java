package ar.edu.itba.sia.model.character.warrior;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

import java.util.List;

public abstract class Warrior extends Character {

    private static final double ATTACK_FACTOR = 0.6;
    private static final double DEFENSE_FACTOR = 0.4;

    public Warrior(final double height, final List<Equipment> equipment) {
        super(height, equipment);
    }

    public Warrior(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                    final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);
    }

    @Override
    public double attackFactor() {
        return ATTACK_FACTOR;
    }

    @Override
    public double defenseFactor() {
        return DEFENSE_FACTOR;
    }
}
