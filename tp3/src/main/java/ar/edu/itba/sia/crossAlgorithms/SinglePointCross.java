package ar.edu.itba.sia.crossAlgorithms;

import ar.edu.itba.sia.interfaces.CrossAlgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class SinglePointCross implements CrossAlgorithm {
	
	@Override
	public void cross(List<Character> newGen, Character character1, Character character2) {
		int equipmentQuantity = character1.getEquipmentQuantity();

		int crossPoint = ThreadLocalRandom.current().nextInt(0, equipmentQuantity);

		List<Equipment> equipmentFather1 = character1.getEquipment();
		List<Equipment> equipmentFather2 = character2.getEquipment();

		List<Equipment> equipmentSon1 = new LinkedList<>();
		List<Equipment> equipmentSon2 = new LinkedList<>();

		for (int i=0 ; i < crossPoint ; i++) {
			equipmentSon1.add(equipmentFather1.get(i));
			equipmentSon2.add(equipmentFather2.get(i));
		}
	
		for (int i=crossPoint ; i < equipmentQuantity ; i++) {
			equipmentSon1.add(equipmentFather2.get(i));
			equipmentSon2.add(equipmentFather1.get(i));
		}

		double heightSon1, heightSon2;
		if (crossPoint < (equipmentQuantity)/2) {
			heightSon1= character1.getHeight();
			heightSon2= character2.getHeight();
		}else {
			heightSon1= character2.getHeight();
			heightSon2= character1.getHeight();
		}

		Character son1 = character1.newSon(heightSon1, equipmentSon1);
		Character son2 = character2.newSon(heightSon2, equipmentSon2);

		newGen.add(son1);
		newGen.add(son2);
	}

}
