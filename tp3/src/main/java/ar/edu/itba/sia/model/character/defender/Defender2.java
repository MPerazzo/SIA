package ar.edu.itba.sia.model.character.defender;

public class Defender2 extends Defender {

    @Override
    public double getStrengthFactor() {
        return 1.1;
    }

    @Override
    public double getAgilityFactor() {
        return 0.8;
    }

    @Override
    public double getDexterityFactor() {
        return 0.8;
    }

    @Override
    public double getResistanceFactor() {
        return 1.1;
    }

    @Override
    public double getHealthFactor() {
        return 1.1;
    }
}
