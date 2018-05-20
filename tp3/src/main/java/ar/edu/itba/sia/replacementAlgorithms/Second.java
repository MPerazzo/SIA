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
    public List<Character> newGeneration(List<Character> sons, List<Character> oldGeneration) {
        List<Character> newGen = new LinkedList<>();
        if(sons != null) {
            newGen.addAll(sons);
        }
        newGen.addAll(selectionAlgorithm.select(oldGeneration));
        return newGen;
    }
}
