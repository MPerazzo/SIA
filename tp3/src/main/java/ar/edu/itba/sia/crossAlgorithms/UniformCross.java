package ar.edu.itba.sia.crossAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.archer.Archer1;
import ar.edu.itba.sia.model.character.archer.Archer2;
import ar.edu.itba.sia.model.character.archer.Archer3;
import ar.edu.itba.sia.model.character.assasin.Assassin1;
import ar.edu.itba.sia.model.character.assasin.Assassin2;
import ar.edu.itba.sia.model.character.assasin.Assassin3;
import ar.edu.itba.sia.model.character.defender.Defender1;
import ar.edu.itba.sia.model.character.defender.Defender2;
import ar.edu.itba.sia.model.character.defender.Defender3;
import ar.edu.itba.sia.model.character.warrior.Warrior1;
import ar.edu.itba.sia.model.character.warrior.Warrior2;
import ar.edu.itba.sia.model.character.warrior.Warrior3;
import ar.edu.itba.sia.model.equipment.Armor;
import ar.edu.itba.sia.model.equipment.Boots;
import ar.edu.itba.sia.model.equipment.Equipment;
import ar.edu.itba.sia.model.equipment.Gloves;
import ar.edu.itba.sia.model.equipment.Helmet;
import ar.edu.itba.sia.model.equipment.Weapon;

public class UniformCross implements CrossAlgorithm<Character> {

	private static final double p = 0.5;
	private double probability= ThreadLocalRandom.current().nextDouble(0,1);
	
	@Override
	public List<Character> cross(Character character1, Character character2, double pc) {
		List<Character> sons= new ArrayList<Character>();
		double [] probabilities = new double[Character.allelsQuantity];
		Equipment [] equipment1 = new Equipment[Character.allelsQuantity-1]; 
		Equipment [] equipment2 = new Equipment[Character.allelsQuantity-1];
		Character son1 = null;
		Character son2 = null;
		double height1, height2;
						
		if (probability>pc) {
			return null;
		}
		
		for (int i=0; i<Character.allelsQuantity; i++) {
			probabilities[i]=ThreadLocalRandom.current().nextDouble(0,1);
		}
		
		for (int i=0; i<Character.allelsQuantity-1; i++) {
			if(probabilities[i]< p) {
				equipment1[i]= character1.getEquipment().get(i);
				equipment2[i]= character2.getEquipment().get(i);
			}else {
				equipment1[i]= character2.getEquipment().get(i);
				equipment2[i]= character1.getEquipment().get(i);
			}	
		}
		
		if(probabilities[5]< p) {
			height1= character1.getHeight();
			height2= character2.getHeight();
		}else {
			height1= character2.getHeight();
			height2= character1.getHeight();
		}	
		
		
		
		if (character1 instanceof Warrior1) {
			son1 = new Warrior1(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Warrior1(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Warrior2) {
			son1 = new Warrior2(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Warrior2(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Warrior3) {
			son1 = new Warrior3(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Warrior3(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Archer1) {
			son1 = new Archer1(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Archer1(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Archer2) {
			son1 = new Archer2(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Archer2(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Archer3) {
			son1 = new Archer3(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Archer3(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Assassin1) {
			son1 = new Assassin1(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Assassin1(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}	

		if (character1 instanceof Assassin2) {
			son1 = new Assassin2(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Assassin2(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}	

		if (character1 instanceof Assassin3) {
			son1 = new Assassin3(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Assassin3(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Defender1) {
			son1 = new Defender1(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Defender1(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Defender2) {
			son1 = new Defender2(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Defender2(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}
		
		if (character1 instanceof Defender3) {
			son1 = new Defender3(height1, (Armor)equipment1[0], (Boots)equipment1[1], (Gloves)equipment1[2], (Helmet)equipment1[3], (Weapon)equipment1[4]);
			son1 = new Defender3(height2, (Armor)equipment2[0], (Boots)equipment2[1], (Gloves)equipment2[2], (Helmet)equipment2[3], (Weapon)equipment2[4]);
		}

		sons.add(son1);
		sons.add(son2);
		
		return sons;
		
	}

}
