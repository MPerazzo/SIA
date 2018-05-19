package ar.edu.itba.sia.mutationAlgorithms;

import java.util.Collections;
import java.util.List;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

public class ShuffleMutation implements MutationAlgorithm<Character> {

	@Override
	public Character mute(Character character, double pc) {
		
		List<Equipment>equipments= character.getEquipment();
		
		Collections.shuffle(equipments);
		
		character.setArmor(equipments.get(0));
		character.setBoots(equipments.get(1));
		character.setGloves(equipments.get(2));
		character.setHelmet(equipments.get(3));
		character.setWeapon(equipments.get(4));
		
		return character;
	}

}
