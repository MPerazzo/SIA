package ar.edu.itba.sia.interfaces;


import ar.edu.itba.sia.model.character.Character;

public interface MutationAlgorithm<T> {
	
		Character mute(T character, double pc);

}