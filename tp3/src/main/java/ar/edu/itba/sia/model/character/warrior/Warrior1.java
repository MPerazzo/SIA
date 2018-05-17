package ar.edu.itba.sia.model.character.warrior;

public class Warrior1 extends Warrior {

    @Override
    public double getStrengthFactor() {
        return 1.1;
    }

    @Override
    public double getAgilityFactor() {
        return 0.9;
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
