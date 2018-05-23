package ar.edu.itba.sia.model.character.assassin;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

import java.util.List;

public abstract class Assassin extends Character {

    private static final double ATTACK_FACTOR = 0.7;
    private static final double DEFENSE_FACTOR = 0.3;

    public Assassin (final double height, final List<Equipment> equipment) {
        super(height, equipment);
    }

    public Assassin(final double height, final Armor armor, final Boots boots, final Gloves gloves,
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
