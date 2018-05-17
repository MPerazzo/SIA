package ar.edu.itba.sia.model.character.defender;

public class Defender3 extends Defender {

    @Override
    public double getStrengthFactor() {
        return 0.9;
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
        return 1.0;
    }

    @Override
    public double getHealthFactor() {
        return 1.3;
    }
}
