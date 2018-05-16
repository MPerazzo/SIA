package ar.edu.itba.sia.model.equipment;

public abstract class Equipment {

    private double strength;
    private double agility;
    private double expertise;
    private double life;
    private double resistance;

    public Equipment(final double strength, final double agility, final double expertise,
                     final double life, final double resistence) {
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.life = life;
        this.resistance = resistence;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getExpertise() {
        return expertise;
    }

    public double getLife() {
        return life;
    }

    public double getResistance() {
        return resistance;
    }
}
