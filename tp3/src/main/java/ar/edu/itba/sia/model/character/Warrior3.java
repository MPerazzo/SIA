package ar.edu.itba.sia.model.character;

public class Warrior3 extends Character {

    @Override
    public double getStrengthFactor() {
        return 0.8;
    }

    @Override
    public double getAgilityFactor() {
        return 0.9;
    }

    @Override
    public double getExpertiseFactor() {
        return 0.9;
    }

    @Override
    public double getResistanceFactor() {
        return 1.2;
    }

    @Override
    public double getLifeFactor() {
        return 1.1;
    }
}
