package ar.edu.itba.sia.model.character;

import ar.edu.itba.sia.model.equipment.*;

import java.util.LinkedList;
import java.util.List;

public abstract class Character {

    private double height;

    private double strength = 0;
    private double agility = 0;
    private double dexterity = 0;
    private double health = 0;
    private double resistance = 0;

    private Armor armor;
    private Boots boots;
    private Gloves gloves;
    private Helmet helmet;
    private Weapon weapon;

    public Character(final double height, final Armor armor, final Boots boots, final Gloves gloves,
                     final Helmet helmet, final Weapon weapon) {

        this.height = height;

        this.armor = armor;
        this.boots = boots;
        this.gloves = gloves;
        this.helmet = helmet;
        this.weapon = weapon;

        List<Equipment> equipment = new LinkedList<>();
        equipment.add(armor);
        equipment.add(boots);
        equipment.add(gloves);
        equipment.add(helmet);
        equipment.add(weapon);

        for (Equipment e : equipment) {
            strength += e.getStrength();
            agility += e.getAgility();
            dexterity += e.getDexterity();
            health += e.getHealth();
            resistance += e.getResistance();
        }

        strength = strength * strengthFactor();
        agility = agility * agilityFactor();
        dexterity = dexterity * dexterityFactor();
        resistance = resistance * resistanceFactor();
        health = health * healthFactor();
    }

    public double getHeight() {
        return height;
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

    public Armor getArmor() {
        return armor;
    }

    public Boots getBoots() {
        return boots;
    }

    public Gloves getGloves() {
        return gloves;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public abstract double getAttackFactor();

    public abstract double getDefenseFactor();

    protected abstract double strengthFactor();

    protected abstract double agilityFactor();

    protected abstract double dexterityFactor();

    protected abstract double resistanceFactor();

    protected abstract double healthFactor();

}
