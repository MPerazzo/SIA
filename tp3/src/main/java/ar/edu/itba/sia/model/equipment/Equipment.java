package ar.edu.itba.sia.model.equipment;

public abstract class Equipment {

    private int id;
  	private double strength;
    private double agility;
    private double dexterity;
    private double health;
    private double resistance;

    public Equipment(final int id, final double strength, final double agility, final double dexterity,
                     final double health, final double resistance) {
        this.id = id;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.health = health;
        this.resistance = resistance;
    }

    public int getId() {
        return id;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getDexterity() {
        return dexterity;
    }

    public double getHealth() {
        return health;
    }

    public double getResistance() {
        return resistance;
    }
}
