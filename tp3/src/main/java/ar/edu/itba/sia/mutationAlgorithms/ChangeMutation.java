package ar.edu.itba.sia.mutationAlgorithms;

import java.util.concurrent.ThreadLocalRandom;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class ChangeMutation implements MutationAlgorithm {

	@Override
	public Character mutate(Character character, double pc) {
		int equipmentQuantity = character.getEquipmentQuantity();
		int gen1= ThreadLocalRandom.current().nextInt(0, equipmentQuantity);
		int gen2= ThreadLocalRandom.current().nextInt(0, equipmentQuantity);
		Equipment equipment1, equipment2;
						
		if (gen1 != gen2) {
			equipment1 = character.getEquipment().get(gen1);
			equipment2 = character.getEquipment().get(gen2);
			character.getEquipment().set(gen1, equipment2);
			character.getEquipment().set(gen2, equipment1);
		}
		
		character.setArmor(character.getEquipment().get(0));
		character.setBoots(character.getEquipment().get(1));
		character.setGloves(character.getEquipment().get(2));
		character.setHelmet(character.getEquipment().get(3));
		character.setWeapon(character.getEquipment().get(4));
		

		return character;
	}

}
