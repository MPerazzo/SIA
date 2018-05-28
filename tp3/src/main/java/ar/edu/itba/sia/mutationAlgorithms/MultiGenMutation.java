package ar.edu.itba.sia.mutationAlgorithms;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.ConfigurationManager;

import java.util.concurrent.ThreadLocalRandom;

public class MultiGenMutation implements MutationAlgorithm {

	private final ConfigurationManager m;
	private final RandomSeeded r;

	public MultiGenMutation(ConfigurationManager m) {
		this.m = m;
		this.r = m.getRandomSeeded();
	}

	@Override
	public void mutate(Character character) {
		int equipmentQuantity = character.getEquipmentQuantity();

		for (int i = 0 ; i < equipmentQuantity + 1 ; i++) {
			double rand = r.nextDouble();
			if (rand > 0.5)
				mutateIndex(i, character);
		}
	}

	private void mutateIndex(int muteIndex, Character character) {

		int random;

		switch (muteIndex) {
			case Character.ARMOR_SLOT:
				random = r.nextInt(0, m.getArmors().size());
				character.setArmor(m.getArmors().get(random));
				break;
			case Character.BOOTS_SLOT:
				random = r.nextInt(0, m.getBoots().size());
				character.setBoots(m.getBoots().get(random));
				break;
			case Character.GLOVES_SLOT:
				random = r.nextInt(0, m.getGloves().size());
				character.setGloves(m.getGloves().get(random));
				break;
			case Character.HELMET_SLOT:
				random = r.nextInt(0, m.getHelmets().size());
				character.setHelmet(m.getHelmets().get(random));
				break;
			case Character.WEAPON_SLOT:
				random = r.nextInt(0, m.getWeapons().size());
				character.setWeapon(m.getWeapons().get(random));
				break;
			case Character.HEIGHT_SLOT:
				character.setHeight(r.nextDouble(ConfigurationManager.HEIGHT_START,
						ConfigurationManager.HEIGHT_END));
				break;
		}
	}
}
