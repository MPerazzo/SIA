package ar.edu.itba.sia.model.character.warrior;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

import java.util.List;

public class Warrior1 extends Warrior {

    private static final double STRENGTH_FACTOR = 1.1;
    private static final double AGILITY_FACTOR = 0.9;
    private static final double DEXTERITY_FACTOR = 0.8;
    private static final double RESISTANCE_FACTOR = 1.0;
    private static final double HEALTH_FACTOR = 0.9;

    public Warrior1(final double height, final List<Equipment> equipment) {
        super(height, equipment);
    }

    public Warrior1(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                    final Helmet helmet, final Weapon weapon) {
        super(height, armor, boots, gloves, helmet, weapon);
    }

    @Override
    public Character newSon(double height, List<Equipment> equipment) {
        return new Warrior1(height, equipment);
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
