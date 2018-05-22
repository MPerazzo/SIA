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
        int replacementCant1;
        int replacementCant2;

        ReplacementMethod replacementMethod1 = ReplacementMethod.
                getReplacementMethod(m.getFirstSelectionMethodReplacement());

        ReplacementMethod replacementMethod2 = ReplacementMethod.
                getReplacementMethod(m.getSecondSelectionMethodReplacement());

        if (replacementMethod1.equals(ReplacementMethod.SECOND))
            replacementCant1 = (int) Math.floor(replacementPercent * populationCant - selectionCant1);
        else if (replacementMethod1.equals(ReplacementMethod.FIRST)) {
            replacementCant1 = (int) Math.floor(replacementPercent * populationCant);
            selectionCant1 = replacementCant1;
        } else
            replacementCant1 = (int) Math.floor(replacementPercent * populationCant);

        if (replacementMethod2.equals(ReplacementMethod.SECOND))
            replacementCant2 = (int) Math.floor((1 - replacementPercent) * populationCant - selectionCant2);
        else if (replacementMethod2.equals(ReplacementMethod.FIRST)) {
            replacementCant2 = (int) Math.floor((1 - replacementPercent) * populationCant);
            selectionCant2 = replacementCant2;
        } else
            replacementCant2 = (int) Math.floor((1 - replacementPercent) * populationCant);


        SelectionAlgorithm selectionAlgorithm1 = SelectionMethod.
                getSelectionAlgorithm(m.getFirstSelectionMethod(), selectionCant1, m);
        SelectionAlgorithm selectionAlgorithm2 = SelectionMethod.
                getSelectionAlgorithm(m.getSecondSelectionMethod(), selectionCant2, m);

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod());

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        SelectionAlgorithm selectionAlgorithmReplacement1 = SelectionMethod.
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

            List<Character> newGen = Crossing.randomCross(selected, crossAlgorithm, crossingProb);

            Mutation.mutate(newGen, mutationAlgorithm, m.getMutationProb());

            replacementAlgorithm1.newGeneration(newGen, currentGeneration, selectionAlgorithmReplacement1);
            replacementAlgorithm2.newGeneration(newGen, currentGeneration, selectionAlgorithmReplacement2);
        }
    }
}
