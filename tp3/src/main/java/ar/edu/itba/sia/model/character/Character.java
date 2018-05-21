package ar.edu.itba.sia.model.character;

import ar.edu.itba.sia.model.Modifier;
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

    private double fitness;

    private List<Equipment> equipment = new LinkedList<>();

    private Armor armor; 
	private Boots boots;
    private Gloves gloves;
    private Helmet helmet;
    private Weapon weapon;

    public Character (final double height, final Armor armor, final Boots boots, final Gloves gloves,
                      final Helmet helmet, final Weapon weapon) {
        this.height = height;

        this.armor = armor;
        this.boots = boots;
        this.gloves = gloves;
        this.helmet = helmet;
        this.weapon = weapon;

        this.equipment.add(armor);
        this.equipment.add(boots);
        this.equipment.add(gloves);
        this.equipment.add(helmet);
        this.equipment.add(weapon);

        this.calculateAttributes();
    }

    public Character(final double height, final List<Equipment> equipment) {

        this.height = height;

        this.armor = (Armor) equipment.get(0);
        this.boots = (Boots) equipment.get(1);
        this.gloves = (Gloves) equipment.get(2);
        this.helmet = (Helmet) equipment.get(3);
        this.weapon = (Weapon) equipment.get(4);

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

    public List<Equipment> getEquipment() {
    	return equipment;
    }
    
    public void setHeight(double height) {
		this.height = height;
	}

	public void setArmor(Equipment armor) {
		this.armor = (Armor) armor;
	}

	public void setBoots(Equipment boots) {
		this.boots = (Boots) boots;
	}

	public void setGloves(Equipment gloves) {
		this.gloves = (Gloves) gloves;
	}

	public void setHelmet(Equipment helmet) {
		this.helmet = (Helmet) helmet;
	}

	public void setWeapon(Equipment weapon) {
		this.weapon = (Weapon) weapon;
	}
    
    protected abstract double attackFactor();

    protected abstract double defenseFactor();

    protected abstract double strengthFactor();

    protected abstract double agilityFactor();

    protected abstract double dexterityFactor();

    protected abstract double resistanceFactor();

    protected abstract double healthFactor();
    
    
    

}
