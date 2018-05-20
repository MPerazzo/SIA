package ar.edu.itba.sia.interfaces;

import ar.edu.itba.sia.model.character.Character;

import java.util.List;

public interface ReplacementAlgorithm {

    List<Character> newGeneration(List<Character> sons, List<Character> oldGeneration);
}
