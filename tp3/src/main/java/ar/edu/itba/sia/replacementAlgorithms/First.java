package ar.edu.itba.sia.replacementAlgorithms;

import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.selectionAlgorithms.Roulette;
import ar.edu.itba.sia.utils.Parser;

import java.util.List;

public class First implements ReplacementAlgorithm{

    @Override
    public List<Character> newGeneration(List<Character> children, List<Character> currentGeneration,
                              SelectionAlgorithm firstSelectionAlgorithm,
                              SelectionAlgorithm secondSelectionAlgorithm) {
        return children;
        }
}

