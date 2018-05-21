package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.*;

import java.util.LinkedList;
import java.util.List;

public class GeneticAlgorithm {

    private final ConfigurationManager m;

    public GeneticAlgorithm(Parser p){
        this.m = new ConfigurationManager(p);
    }

    public void geneticAlgorithm() {
        double selectionPercent = m.getSelectionPercent();
        double replacementPercent = m.getReplacementPercent();
        List<Character> initialGeneration = m.getInitialGeneration();
        int populationCant = m.getPopulationCant();
        int selectionCant = m.getSelectionCant();
        int selectionCant1 = (int) Math.floor(selectionPercent * selectionCant);
        int selectionCant2 = selectionCant - selectionCant1;
        int replacementCant1 = (int) Math.floor(replacementPercent * populationCant);
        int replacementCant2 = populationCant - replacementCant1;

        SelectionAlgorithm selectionAlgorithm1 = SelectionMethod.
                getSelectionAlgorithm(m.getFirstSelectionMethod(), selectionCant1, m);
        SelectionAlgorithm selectionAlgorithm2 = SelectionMethod.
                getSelectionAlgorithm(m.getSecondSelectionMethod(), selectionCant2, m);

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod());

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod());

        SelectionAlgorithm selectionAlgorithReplacement1 = SelectionMethod.
                getSelectionAlgorithm(m.getFirstSelectionMethod(), replacementCant1, m);
        SelectionAlgorithm selectionAlgorithmReplacement2 = SelectionMethod.
                getSelectionAlgorithm(m.getSecondSelectionMethod(), replacementCant2, m);

        ReplacementAlgorithm replacementAlgorithm1 = ReplacementMethod.getReplacementAlgorithm(m.getFirstReplacementMethod());
        ReplacementAlgorithm replacementAlgorithm2 = ReplacementMethod.getReplacementAlgorithm(m.getSecondReplacementMethod());

        LinkedList<Character> selected = new LinkedList<>();

        double crossingProb = m.getCrossingProb();

        List<Character> currentGeneration = initialGeneration;

        while(true/*condicion*/) {
            selected.addAll(selectionAlgorithm1.select(currentGeneration));
            selected.addAll(selectionAlgorithm2.select(currentGeneration));

            List<Character> newGen = Crossing.cross(selected, crossAlgorithm, crossingProb);

            Mutation.mutate(newGen, mutationAlgorithm, m.getMutationProb());


        }
    }
}
