package ar.edu.itba.sia.structures;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class Node {

    private Character character;
    private double performance;

    public Node(Character character) {
        this.character = character;

        double attack;
        double defense;
        double h = character.getHeight();
        double strength = 0;
        double agility = 0;
        double expertise = 0;
        double life = 0;
        double resistance = 0;

        for (Equipment e : character.getEquipment()) {
            strength += e.getStrength();
            agility += e.getAgility();
            expertise += e.getExpertise();
            life += e.getLife();
            resistance += e.getResistance();
        }

        strength = 100 * Math.tanh(0.01 * strength * character.getStrengthFactor());
        agility = Math.tanh(0.01 * agility * character.getAgilityFactor());
        expertise = 0.6 * Math.tanh(0.01 * expertise * character.getExpertiseFactor());
        resistance = Math.tanh(0.01 * resistance * character.getResistanceFactor());
        life = 100 * Math.tanh(0.01 * life * character.getLifeFactor());

        double attackMod = 0.5 - Math.pow(3*h - 5, 4) + Math.pow(3*h - 5, 2) + h/2;
        double defMod = 2 + Math.pow(3*h - 5, 4) - Math.pow(3*h - 5, 2) - h/2;

        attack = (agility + expertise) * strength * attackMod;
        defense = (resistance + expertise) * life * defMod;
    }
}
