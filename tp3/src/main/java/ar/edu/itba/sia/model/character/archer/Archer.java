package ar.edu.itba.sia.model.character.archer;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

import java.util.List;

public abstract class Archer extends Character {

    private static final double ATTACK_FACTOR = 0.9;
    private static final double DEFENSE_FACTOR = 0.1;

    public Archer (final double height, final List<Equipment> equipment) {
        super(height, equipment);
    }

    public Archer(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                    final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);
    }

    @Override
    protected double attackFactor() {
        return ATTACK_FACTOR;
    }

    @Override
    protected double defenseFactor() {
        return DEFENSE_FACTOR;
    }
}
