package ar.edu.itba.sia.model.character.archer;

public class Archer1 extends Archer {

    @Override
    public double getStrengthFactor() {
        return 0.8;
    }

    @Override
    public double getAgilityFactor() {
        return 1.1;
    }

    @Override
    public double getDexterityFactor() {
        return 1.1;
    }

    @Override
    public double getResistanceFactor() {
        return 0.9;
    }

    @Override
    public double getHealthFactor() {
        return 0.7;
    }
}
