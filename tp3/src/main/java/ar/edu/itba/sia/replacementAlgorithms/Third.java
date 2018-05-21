package ar.edu.itba.sia.replacementAlgorithms;

import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.selectionAlgorithms.Roulette;
import ar.edu.itba.sia.utils.Parser;

import java.util.List;

public class Third implements ReplacementAlgorithm {

    private SelectionAlgorithm selectionAlgorithm;

    public Third(Parser parser) {
        this.selectionAlgorithm = new Roulette(parser.getPopulationCant()-parser.getSelectionCant());
    }

    @Override
    public void newGeneration(List<Character> newGen, List<Character> oldGeneration) {
        newGen.addAll(oldGeneration);
        List<Character> selected = selectionAlgorithm.select(newGen);
        newGen.clear();
        newGen.addAll(selected);
    }
}
