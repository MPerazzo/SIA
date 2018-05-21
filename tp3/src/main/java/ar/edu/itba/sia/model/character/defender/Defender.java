package ar.edu.itba.sia.model.character.defender;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

import java.util.List;

public abstract class Defender extends Character {

    private static final double ATTACK_FACTOR = 0.1;
    private static final double DEFENSE_FACTOR = 0.9;

    public Defender (final double height, final List<Equipment> equipment) {
        super(height, equipment);
    }

    public Defender(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                    final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);
    }

    @Override
    public double attackFactor() {
        return 0.1;
    }

    @Override
    public double defenseFactor() {
        return 0.9;
    }
}
