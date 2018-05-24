package ar.edu.itba.sia.interfaces;

import ar.edu.itba.sia.model.character.Character;

import java.util.List;

public interface ReplacementAlgorithm {

    List<Character> newGeneration(List<Character> children, List<Character> currentGeneration,
                       SelectionAlgorithm firstSelectionAlgorithm, SelectionAlgorithm secondSelectionAlgorithm);
}
