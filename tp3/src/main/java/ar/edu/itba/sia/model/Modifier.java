package ar.edu.itba.sia.model;

public class Modifier {

    public static double strengthMod(double strength) {
        return 100 * Math.tanh(0.01 * strength);
    }

    public static double agilityMod(double agility) {
        return Math.tanh(0.01 * agility);
    }

    public static double dexterityMod(double dexterity) {
        return 0.6 * Math.tanh(0.01 * dexterity);
    }

    public static double resistanceMod(double resistance) {
        return Math.tanh(0.01 * resistance);
    }

    public static double healthMod(double health) {
        return 100 * Math.tanh(0.01 * health);
    }

    public static double attackMod(double h) {
        return 0.5 - Math.pow(3*h - 5, 4) + Math.pow(3*h - 5, 2) + h/2;
    }

    public static double defenseMod(double h) {
        return 2 + Math.pow(3*h - 5, 4) - Math.pow(3*h - 5, 2) - h/2;
    }
}
