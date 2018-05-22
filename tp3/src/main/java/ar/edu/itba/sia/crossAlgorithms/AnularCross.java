package ar.edu.itba.sia.crossAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
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

public class AnularCross implements CrossAlgorithm {
	
	@Override
	public void cross(List<Character> newGen, Character character1, Character character2) {
		int equipmentQuantity = character1.getEquipmentQuantity();

		int crossPoint = ThreadLocalRandom.current().nextInt(0, equipmentQuantity);
		int segment = ThreadLocalRandom.current().nextInt(0, (equipmentQuantity/2)+1);

		List<Equipment> equipmentFather1 = character1.getEquipment();
		List<Equipment> equipmentFather2 = character2.getEquipment();

		List<Equipment> equipmentSon1 = new LinkedList<>();
		List<Equipment> equipmentSon2 = new LinkedList<>();

		for (int i=0 ; i < crossPoint ; i++) {
			equipmentSon1.add(equipmentFather1.get(i));
			equipmentSon2.add(equipmentFather2.get(i));
		}

		int i = crossPoint;
		for ( ; i < equipmentQuantity - 1 && i - crossPoint < segment; i++) {
			equipmentSon1.add(equipmentFather2.get(i));
			equipmentSon2.add(equipmentFather1.get(i));
		}

		int segmentUses = i - crossPoint;

		if (segmentUses == segment) {
				for (int j = i; j < equipmentQuantity - 1; j++) {
				equipmentSon1.add(equipmentFather1.get(i));
				equipmentSon2.add(equipmentFather2.get(i));
			}
		}
		else {
			for (int j = 0 ; j < segment - segmentUses ; j++) {
				equipmentSon1.add(equipmentFather2.get(i));
				equipmentSon2.add(equipmentFather1.get(i));
			}
		}

		double heightSon1, heightSon2;
		if (crossPoint < (equipmentQuantity)/2) {
			heightSon1= character1.getHeight();
			heightSon2= character2.getHeight();
		} else {
			heightSon1= character2.getHeight();
			heightSon2= character1.getHeight();
		}

		Character son1;
		Character son2;

		if (character1 instanceof Warrior1) {
			son1 = new Warrior1(heightSon1, equipmentSon1);
			son2 = new Warrior1(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Warrior2) {
			son1 = new Warrior2(heightSon1, equipmentSon1);
			son2 = new Warrior2(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Warrior3) {
			son1 = new Warrior3(heightSon1, equipmentSon1);
			son2 = new Warrior3(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Archer1) {
			son1 = new Archer1(heightSon1, equipmentSon1);
			son2 = new Archer1(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Archer2) {
			son1 = new Archer2(heightSon1, equipmentSon1);
			son2 = new Archer2(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Archer3) {
			son1 = new Archer3(heightSon1, equipmentSon1);
			son2 = new Archer3(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Assassin1) {
			son1 = new Assassin1(heightSon1, equipmentSon1);
			son2 = new Assassin1(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Assassin2) {
			son1 = new Assassin2(heightSon1, equipmentSon1);
			son2 = new Assassin2(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Assassin3) {
			son1 = new Assassin3(heightSon1, equipmentSon1);
			son2 = new Assassin3(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Defender1) {
			son1 = new Defender1(heightSon1, equipmentSon1);
			son2 = new Defender1(heightSon2, equipmentSon2);
		}
		else if (character1 instanceof Defender2) {
			son1 = new Defender2(heightSon1, equipmentSon1);
			son2 = new Defender2(heightSon2, equipmentSon2);
		}
		else  {
			son1 = new Defender3(heightSon1, equipmentSon1);
			son2 = new Defender3(heightSon2, equipmentSon2);
		}

		newGen.add(son1);
		newGen.add(son2);
	}

}
