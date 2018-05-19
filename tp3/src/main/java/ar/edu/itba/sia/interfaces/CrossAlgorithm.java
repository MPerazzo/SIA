package ar.edu.itba.sia.interfaces;

import java.util.List;

import ar.edu.itba.sia.model.character.Character;

public interface CrossAlgorithm<T> {
	List<Character> cross(T character1, T character2, double pc);
}
