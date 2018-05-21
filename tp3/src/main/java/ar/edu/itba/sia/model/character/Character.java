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

    private double strength = 0;
    private double agility = 0;
    private double dexterity = 0;
    private double health = 0;
    private double resistance = 0;

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

        strength = Modifier.strengthMod(strength * strengthFactor());
        agility = Modifier.agilityMod(agility * agilityFactor());
        dexterity = Modifier.dexterityMod(dexterity * dexterityFactor());
        resistance = Modifier.resistanceMod(resistance * resistanceFactor());
        health = Modifier.healthMod(health * healthFactor());

        double attackMod = Modifier.attackMod(height);
        double defMod = Modifier.defenseMod(height);

        double attack = (agility + dexterity) * strength * attackMod;
        double defense = (resistance + dexterity) * health * defMod;

        fitness = this.attackFactor() * attack + this.defenseFactor() * defense;
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
	}

	public void setArmor(Equipment armor) {
		equipment.set(ARMOR_SLOT, armor);
	}

	public void setBoots(Equipment boots) {
		equipment.set(BOOTS_SLOT, boots);
	}

	public void setGloves(Equipment gloves) {
		equipment.set(GLOVES_SLOT, gloves);
	}

	public void setHelmet(Equipment helmet) {
		equipment.set(HELMET_SLOT, helmet);
	}

	public void setWeapon(Equipment weapon) {
		equipment.set(WEAPON_SLOT, weapon);
	}
    
    protected abstract double attackFactor();

    protected abstract double defenseFactor();

    protected abstract double strengthFactor();

    protected abstract double agilityFactor();

    protected abstract double dexterityFactor();

    protected abstract double resistanceFactor();

    protected abstract double healthFactor();
    
    
    

}
