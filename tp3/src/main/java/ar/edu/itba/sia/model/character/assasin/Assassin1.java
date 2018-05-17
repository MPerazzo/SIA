package ar.edu.itba.sia.model.character.assasin;

import ar.edu.itba.sia.model.equipment.*;

public class Assassin1 extends Assassin {

    private static final double STRENGTH_FACTOR = 0.8;
    private static final double AGILITY_FACTOR = 1.2;
    private static final double DEXTERITY_FACTOR = 1.1;
    private static final double RESISTANCE_FACTOR = 1.0;
    private static final double HEALTH_FACTOR = 0.8;

    public Assassin1(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                   final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);
    }

    @Override
    protected double strengthFactor() {
        return STRENGTH_FACTOR;
    }

    @Override
    protected double agilityFactor() {
        return AGILITY_FACTOR;
    }

    @Override
    protected double dexterityFactor() {
        return DEXTERITY_FACTOR;
    }

    @Override
    protected double resistanceFactor() {
        return RESISTANCE_FACTOR;
    }

    @Override
    protected double healthFactor() {
        return HEALTH_FACTOR;
    }
}
