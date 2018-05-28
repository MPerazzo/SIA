package ar.edu.itba.sia.crossAlgorithms;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class UniformCross implements CrossAlgorithm {

	private static final double p = 0.5;

	public RandomSeeded r;

	public UniformCross(RandomSeeded r) {
		this.r = r;
	}

	@Override
	public void cross(List<Character> newGen, Character character1, Character character2) {
		int equipmentQuantity = character1.getEquipmentQuantity();

		double [] probabilities = new double[equipmentQuantity];

		List<Equipment> equipmentFather1 = character1.getEquipment();
		List<Equipment> equipmentFather2 = character2.getEquipment();

		List<Equipment> equipmentSon1 = new LinkedList<>();
		List<Equipment> equipmentSon2 = new LinkedList<>();

		for (int i=0 ; i < equipmentQuantity ; i++) {
			probabilities[i] = r.nextDouble();
		}

		for (int i=0 ; i < equipmentQuantity ; i++) {
			if (probabilities[i] < p) {
				equipmentSon1.add(equipmentFather1.get(i));
				equipmentSon2.add(equipmentFather2.get(i));
			}else {
				equipmentSon1.add(equipmentFather2.get(i));
				equipmentSon2.add(equipmentFather1.get(i));
			}
		}

		double heightSon1, heightSon2;
		if (probabilities[equipmentQuantity - 1] < p) {
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
