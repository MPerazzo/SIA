package ar.edu.itba.sia.mutationAlgorithms;

import java.util.concurrent.ThreadLocalRandom;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.ConfigurationManager;

public class GenMutation implements MutationAlgorithm {

	private final ConfigurationManager m;

	public GenMutation (ConfigurationManager m) {
		this.m = m;
	}

	@Override
	public void mutate(Character character) {

		int equipmentQuantity = character.getEquipmentQuantity();
		int muteIndex = ThreadLocalRandom.current().nextInt(0, equipmentQuantity + 1);

		int random = ThreadLocalRandom.current().nextInt(0, m.getArmors().size());

		switch (muteIndex) {
			case Character.ARMOR_SLOT:
				character.setArmor(m.getArmors().get(random));
				break;
			case Character.BOOTS_SLOT:
				character.setBoots(m.getBoots().get(random));
				break;
			case Character.GLOVES_SLOT:
				character.setGloves(m.getGloves().get(random));
				break;
			case Character.HELMET_SLOT:
				character.setHelmet(m.getHelmets().get(random));
				break;
			case Character.WEAPON_SLOT:
				character.setWeapon(m.getWeapons().get(random));
				break;
			case Character.HEIGHT_SLOT:
				character.setHeight(ThreadLocalRandom.current().nextDouble(ConfigurationManager.HEIGHT_START,
						ConfigurationManager.HEIGHT_END));
				break;
		}
	}
	
	
}
