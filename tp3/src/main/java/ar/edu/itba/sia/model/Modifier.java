package ar.edu.itba.sia.model;

public class Modifier {

    public static double strengthMod(double strength) {
        return 100 * Math.tanh(0.01 * strength);
    }
}
