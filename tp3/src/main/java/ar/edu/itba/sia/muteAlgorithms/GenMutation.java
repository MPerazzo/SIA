package ar.edu.itba.sia.muteAlgorithms;

import java.util.concurrent.ThreadLocalRandom;

import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.model.character.Character;

public class GenMutation implements MutationAlgorithm<Character> {

	@Override
	public Character mute(Character character, double pc) {
		
		int gen= ThreadLocalRandom.current().nextInt(0,Character.allelsQuantity-1);
		
		// TODO Auto-generated method stub
		
		return character;
	}
	
	
}
