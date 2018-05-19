package ar.edu.itba.sia.interfaces;

import java.util.List;

import ar.edu.itba.sia.model.character.Character;

public interface CrossAlgorithm {
	List<Character> cross(Character character1, Character character2, double pc);
}
