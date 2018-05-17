package ar.edu.itba.sia.model.character.archer;

import ar.edu.itba.sia.model.equipment.*;

public class Archer2 extends Archer {

    private static final double STRENGTH_FACTOR = 0.9;
    private static final double AGILITY_FACTOR = 1.1;
    private static final double DEXTERITY_FACTOR = 1.0;
    private static final double RESISTANCE_FACTOR = 0.9;
    private static final double HEALTH_FACTOR = 0.8;

    public Archer2(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                   final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);

        strength = strength * STRENGTH_FACTOR;
        agility = agility * AGILITY_FACTOR;
        dexterity = dexterity * DEXTERITY_FACTOR;
        resistance = resistance * RESISTANCE_FACTOR;
        health = health * HEALTH_FACTOR;

    }
}
