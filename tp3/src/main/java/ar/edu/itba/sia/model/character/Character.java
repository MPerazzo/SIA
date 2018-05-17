package ar.edu.itba.sia.model.character;

import ar.edu.itba.sia.model.equipment.*;

import java.util.LinkedList;
import java.util.List;

public abstract class Character {

    private double height;

    private List<Equipment> equipment = new LinkedList<>();
    private Armor armor;
    private Boots boots;
    private Gloves gloves;
    private Helmet helmet;
    private Weapon weapon;

    public Character() {
        equipment.add(armor);
        equipment.add(boots);
        equipment.add(gloves);
        equipment.add(helmet);
        equipment.add(weapon);
    }

    public List<Equipment> getEquipment() {
        return equipment;
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

    public double getHeight() {
        return height;
    }

    public abstract double getAttackFactor();

    public abstract double getDefenseFactor();

    public abstract double getStrengthFactor();

    public abstract double getAgilityFactor();

    public abstract double getDexterityFactor();

    public abstract double getResistanceFactor();

    public abstract double getHealthFactor();
}
