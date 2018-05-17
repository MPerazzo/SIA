package ar.edu.itba.sia.model.character.defender;

public class Defender1 extends Defender {

    @Override
    public double getStrengthFactor() {
        return 1.0;
    }

    @Override
    public double getAgilityFactor() {
        return 0.9;
    }

    @Override
    public double getDexterityFactor() {
        return 0.7;
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
