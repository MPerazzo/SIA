package ar.edu.itba.sia.model.character.assasin;

/**
 * Created by matias on 17/05/18.
 */
public class Assassin3 extends Assassin {

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
        return 1.0;
    }

    @Override
    public double getResistanceFactor() {
        return 1.1;
    }

    @Override
    public double getHealthFactor() {
        return 1.0;
    }
}
