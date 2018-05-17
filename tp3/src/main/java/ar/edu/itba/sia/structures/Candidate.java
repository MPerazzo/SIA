package ar.edu.itba.sia.structures;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class Candidate {

    private Character character;
    private double performance;

    public Candidate(Character character) {
        this.character = character;

        double attack;
        double defense;
        double h = character.getHeight();
        double strength = 0;
        double agility = 0;
        double dexterity = 0;
        double health = 0;
        double resistance = 0;

        for (Equipment e : character.getEquipment()) {
            strength += e.getStrength();
            agility += e.getAgility();
            dexterity += e.getDexterity();
            health += e.getHealth();
            resistance += e.getResistance();
        }

        strength = 100 * Math.tanh(0.01 * strength * character.getStrengthFactor());
        agility = Math.tanh(0.01 * agility * character.getAgilityFactor());
        dexterity = 0.6 * Math.tanh(0.01 * dexterity * character.getDexterityFactor());
        resistance = Math.tanh(0.01 * resistance * character.getResistanceFactor());
        health = 100 * Math.tanh(0.01 * health * character.getHealthFactor());

        double attackMod = 0.5 - Math.pow(3*h - 5, 4) + Math.pow(3*h - 5, 2) + h/2;
        double defMod = 2 + Math.pow(3*h - 5, 4) - Math.pow(3*h - 5, 2) - h/2;

        attack = (agility + dexterity) * strength * attackMod;
        defense = (resistance + dexterity) * health * defMod;

        performance = character.getAttackFactor() * attack + character.getDefenseFactor() * defense;
    }
}
