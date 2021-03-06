package ar.edu.itba.sia.crossAlgorithms;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class AnnularCross implements CrossAlgorithm {

	public RandomSeeded r;

	public AnnularCross(RandomSeeded r) {
		this.r = r;
	}
	
	@Override
	public void cross(List<Character> newGen, Character character1, Character character2) {
		int equipmentQuantity = character1.getEquipmentQuantity();

		int crossPoint = r.nextInt(0, equipmentQuantity);
		int segment = r.nextInt(1, (equipmentQuantity/2)+1);

		List<Equipment> equipmentFather1 = character1.getEquipment();
		List<Equipment> equipmentFather2 = character2.getEquipment();

		List<Equipment> equipmentSon1 = new LinkedList<>();
		List<Equipment> equipmentSon2 = new LinkedList<>();

		for (int i=0 ; i < crossPoint ; i++) {
			equipmentSon1.add(equipmentFather1.get(i));
			equipmentSon2.add(equipmentFather2.get(i));
		}

		int i = crossPoint;
		for ( ; i < equipmentQuantity && i - crossPoint <= segment; i++) {
			equipmentSon1.add(equipmentFather2.get(i));
			equipmentSon2.add(equipmentFather1.get(i));
		}

		int segmentUses = i - crossPoint - 1;

		if (segmentUses == segment) {
				for (int j = i; j < equipmentQuantity ; j++) {
				equipmentSon1.add(equipmentFather1.get(j));
				equipmentSon2.add(equipmentFather2.get(j));
			}
		}
		else {
			for (int j = 0 ; j < segment - segmentUses ; j++) {
				equipmentSon1.set(j, equipmentFather2.get(j));
				equipmentSon2.set(j, equipmentFather1.get(j));
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

		Character son1 = character1.newSon(heightSon1, equipmentSon1);
		Character son2 = character2.newSon(heightSon2, equipmentSon2);

		newGen.add(son1);
		newGen.add(son2);
	}

}
