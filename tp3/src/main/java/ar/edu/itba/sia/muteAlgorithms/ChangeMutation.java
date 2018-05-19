package ar.edu.itba.sia.muteAlgorithms;

import java.util.concurrent.ThreadLocalRandom;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class ChangeMutation implements MutationAlgorithm<Character> {

	@Override
	public Character mute(Character character, double pc) {
		int gen1= ThreadLocalRandom.current().nextInt(0,Character.allelsQuantity-1);
		int gen2= ThreadLocalRandom.current().nextInt(0,Character.allelsQuantity-1);
		Equipment equipment1, equipment2;
						
		if (gen1 != gen2) {
			equipment1 = character.getEquipments().get(gen1);
			equipment2 = character.getEquipments().get(gen2);
			character.getEquipments().set(gen1, equipment2);
			character.getEquipments().set(gen2, equipment1);
		}
		
		character.setArmor(character.getEquipments().get(0));
		character.setBoots(character.getEquipments().get(1));
		character.setGloves(character.getEquipments().get(2));
		character.setHelmet(character.getEquipments().get(3));
		character.setWeapon(character.getEquipments().get(4));
		

		return character;
	}

}
