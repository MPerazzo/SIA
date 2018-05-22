package ar.edu.itba.sia.model.character;

import ar.edu.itba.sia.model.Modifier;
import ar.edu.itba.sia.model.equipment.*;

import java.util.LinkedList;
import java.util.List;

public abstract class Character {

    public static final int ARMOR_SLOT = 0;
    public static final int BOOTS_SLOT = 1;
    public static final int GLOVES_SLOT = 2;
    public static final int HELMET_SLOT = 3;
    public static final int WEAPON_SLOT = 4;
    public static final int HEIGHT_SLOT = 5;

    private double height;

    private double strength;
    private double agility;
    private double dexterity;
    private double health;
    private double resistance;

    private double strengthMod;
    private double agilityMod;
    private double dexterityMod;
    private double healthMod;
    private double resistanceMod;

    private double attackMod;
    private double defMod;

    private double fitness;

    private List<Equipment> equipment = new LinkedList<>();


    public Character (final double height, final Armor armor, final Boots boots, final Gloves gloves,
                      final Helmet helmet, final Weapon weapon) {
        this.height = height;

        this.equipment.add(armor);
        this.equipment.add(boots);
        this.equipment.add(gloves);
        this.equipment.add(helmet);
        this.equipment.add(weapon);

        this.calculateAttributes();
    }

    public Character(final double height, final List<Equipment> equipment) {

        this.height = height;

        this.equipment.addAll(equipment);

        this.calculateAttributes();
    }

    private void calculateAttributes() {

        for (Equipment e : equipment) {
            strength += e.getStrength();
            agility += e.getAgility();
            dexterity += e.getDexterity();
            health += e.getHealth();
            resistance += e.getResistance();
        }

        calculateAttsMod();

        calculateHeightMod();

        calculateFitness();
    }
    
    public double getHeight() {
        return height;
    }

    public double getFitness() { return fitness; }

    public int getEquipmentQuantity() {
        return equipment.size();
    }

    public Armor getArmor() {
        return (Armor) equipment.get(ARMOR_SLOT);
    }

    public Boots getBoots() {
        return (Boots) equipment.get(BOOTS_SLOT);
    }

    public Gloves getGloves() {
        return (Gloves) equipment.get(GLOVES_SLOT);
    }

    public Helmet getHelmet() {
        return (Helmet) equipment.get(HELMET_SLOT);
    }

    public Weapon getWeapon() {
        return (Weapon) equipment.get(WEAPON_SLOT);
    }

    public List<Equipment> getEquipment() {
    	return equipment;
    }
    
    public void setHeight(double height) {
		this.height = height;
        calculateHeightMod();
        calculateFitness();
	}

	public void setArmor(Equipment armor) {
        refreshFitness(armor, ARMOR_SLOT);
        equipment.set(ARMOR_SLOT, armor);
	}

	public void setBoots(Equipment boots) {
        refreshFitness(boots, BOOTS_SLOT);
        equipment.set(BOOTS_SLOT, boots);
	}

	public void setGloves(Equipment gloves) {
        refreshFitness(gloves, GLOVES_SLOT);
        equipment.set(GLOVES_SLOT, gloves);
    }

	public void setHelmet(Equipment helmet) {
		refreshFitness(helmet, HELMET_SLOT);
        equipment.set(HELMET_SLOT, helmet);
	}

	public void setWeapon(Equipment weapon) {
        refreshFitness(weapon, WEAPON_SLOT);
        equipment.set(WEAPON_SLOT, weapon);
	}

    private void refreshFitness(Equipment newEquipment, int slot) {
        Equipment toReplace = equipment.get(slot);
        substractStats(toReplace);
        addStats(newEquipment);

        calculateAttsMod();

        calculateFitness();
    }

    private void addStats(Equipment e) {

        strength += e.getStrength() * strengthFactor();
        agility += e.getAgility() * agilityFactor();
        dexterity += e.getDexterity() * dexterityFactor();
        resistance += e.getResistance() * resistanceFactor();
        health += e.getHealth() * healthFactor();
    }

    private void substractStats(Equipment e) {

        strength -= e.getStrength() * strengthFactor();
        agility -= e.getAgility() * agilityFactor();
        dexterity -= e.getDexterity() * dexterityFactor();
        resistance -= e.getResistance() * resistanceFactor();
        health -= e.getHealth() * healthFactor();
    }

    private void calculateFitness() {

        double attack = (agilityMod + dexterityMod) * strengthMod * attackMod;
        double defense = (resistanceMod + dexterityMod) * healthMod * defMod;

        fitness = this.attackFactor() * attack + this.defenseFactor() * defense;
    }

    private void calculateAttsMod() {

        strengthMod = Modifier.strengthMod(strength * strengthFactor());
        agilityMod = Modifier.agilityMod(agility * agilityFactor());
        dexterityMod = Modifier.dexterityMod(dexterity * dexterityFactor());
        resistanceMod = Modifier.resistanceMod(resistance * resistanceFactor());
        healthMod = Modifier.healthMod(health * healthFactor());
    }

    private void calculateHeightMod() {
        attackMod = Modifier.attackMod(height);
        defMod = Modifier.defenseMod(height);
    }
    
    protected abstract double attackFactor();

    protected abstract double defenseFactor();

    protected abstract double strengthFactor();

    protected abstract double agilityFactor();

    protected abstract double dexterityFactor();

    protected abstract double resistanceFactor();

    protected abstract double healthFactor();
    
    
    

}
