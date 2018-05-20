package ar.edu.itba.sia.replacementAlgorithms;

import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.selectionAlgorithms.Roulette;
import ar.edu.itba.sia.utils.Parser;

import java.util.LinkedList;
import java.util.List;

public class First implements ReplacementAlgorithm{

    private static final int selectionCant = 2;
    private int N;
    private SelectionAlgorithm selectionAlgorithm;

    public First(Parser parser) {
        this.N = parser.getPopulation();
        this.selectionAlgorithm = new Roulette(selectionCant);
    }

    @Override
    public void newGeneration(List<Character> newGen, List<Character> oldGeneration) {

        while(newGen.size() < N) {
            //Select
            //Cross
            //Mutation
            //newGen.addAll(new sons);
        }
    }
}
