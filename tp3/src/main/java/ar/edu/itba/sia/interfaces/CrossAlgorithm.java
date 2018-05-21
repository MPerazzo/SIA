package ar.edu.itba.sia.interfaces;

import java.util.List;

import ar.edu.itba.sia.model.character.Character;

public interface CrossAlgorithm {
	void cross(List<Character> newGen, Character character1, Character character2);
}
