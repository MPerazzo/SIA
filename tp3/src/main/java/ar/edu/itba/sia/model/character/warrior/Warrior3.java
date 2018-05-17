package ar.edu.itba.sia.model.character.warrior;

public class Warrior3 extends Warrior {

    @Override
    public double getStrengthFactor() {
        return 0.8;
    }

    @Override
    public double getAgilityFactor() {
        return 0.9;
    }

    @Override
    public double getDexterityFactor() {
        return 0.9;
    }

    @Override
    public double getResistanceFactor() {
        return 1.2;
    }

    @Override
    public double getHealthFactor() {
        return 1.1;
    }
}
