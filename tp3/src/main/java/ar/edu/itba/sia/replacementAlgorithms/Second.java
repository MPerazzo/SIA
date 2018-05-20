package ar.edu.itba.sia.replacementAlgorithms;

import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.selectionAlgorithms.Roulette;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class Second implements ReplacementAlgorithm {

    private SelectionAlgorithm selectionAlgorithm;

    public Second(Parser parser) {
        this.selectionAlgorithm = new Roulette(parser.getPopulation()-parser.getSelectionCant());
    }

    @Override
    public void newGeneration(List<Character> newGen, List<Character> oldGeneration) {
        newGen.addAll(selectionAlgorithm.select(oldGeneration));
    }
}
