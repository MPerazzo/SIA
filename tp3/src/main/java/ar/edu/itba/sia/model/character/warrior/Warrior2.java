package ar.edu.itba.sia.model.character.warrior;

public class Warrior2 extends Warrior {

    @Override
    public double getStrengthFactor() {
        return 1.2;
    }

    @Override
    public double getAgilityFactor() {
        return 1.0;
    }

    @Override
    public double getDexterityFactor() {
        return 0.8;
    }

    @Override
    public double getResistanceFactor() {
        return 0.8;
    }

    @Override
    public double getHealthFactor() {
        return 0.8;
    }
}
