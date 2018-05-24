package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.*;
import ar.edu.itba.sia.utils.enums.CrossingMethod;
import ar.edu.itba.sia.utils.enums.MutationMethod;
import ar.edu.itba.sia.utils.enums.ReplacementMethod;
import ar.edu.itba.sia.utils.enums.SelectionMethod;

import java.util.LinkedList;
import java.util.List;

public class GeneticAlgorithm {

    private final ConfigurationManager m;

    public GeneticAlgorithm(Parser p){
        this.m = new ConfigurationManager(p);
    }

    public void geneticAlgorithm() {
        if (m.getReplacementMethod().equals(ReplacementMethod.FIRST)) {
            geneticAlgorithmFirst();
        }
        else
            geneticAlgorithmOthers();
    }

    private void geneticAlgorithmFirst() {

        LinkedList<Character> currentGeneration = (LinkedList) m.getInitialGeneration();

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod());

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        double crossingProb = m.getCrossingProb();

        while(true) {

            LinkedList<Character> children = (LinkedList) Crossing.randomCross(currentGeneration, crossAlgorithm, crossingProb);

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb());

            currentGeneration = children;
        }
    }

    private void geneticAlgorithmOthers() {

        int population = m.getPopulationCant();
        List<Character> currentGeneration = m.getInitialGeneration();

        double selectionPercent = m.getSelectionPercent();
        double replacementPercent = m.getReplacementPercent();
        int selectionCant = m.getSelectionCant();
        int selectionCantA = (int) Math.floor(selectionPercent * selectionCant);
        int selectionCantB = selectionCant - selectionCantA;

        ReplacementMethod replacementMethod = m.getReplacementMethod();

        int replacementCant = 0;
        switch (replacementMethod) {
            case SECOND:
                replacementCant = population - selectionCant;
                break;
            case THIRD:
                replacementCant = population;
                break;
        }

        int replacementCantA = (int) Math.floor(replacementPercent * replacementCant);
        int replacementCantB = replacementCant - replacementCantA;

        SelectionAlgorithm selectionAlgorithmA = SelectionMethod.
                getSelectionAlgorithm(m.getSelectionMethodA(), selectionCantA, m);
        SelectionAlgorithm selectionAlgorithmB = SelectionMethod.
                getSelectionAlgorithm(m.getSelectionMethodB(), selectionCantB, m);

        SelectionAlgorithm selectionAlgorithmReplacementA = SelectionMethod.
                getSelectionAlgorithm(m.getReplacementSelectionMethodA(), replacementCantA, m);
        SelectionAlgorithm selectionAlgorithmReplacementB = SelectionMethod.
                getSelectionAlgorithm(m.getReplacementSelectionMethodB(), replacementCantB, m);

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod());

        double crossingProb = m.getCrossingProb();

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        ReplacementAlgorithm replacementAlgorithm = ReplacementMethod.getReplacementAlgorithm(replacementMethod);

        LinkedList<Character> selectedParents = new LinkedList<>();

        while(true) {
            selectedParents.addAll(selectionAlgorithmA.select(currentGeneration));
            selectedParents.addAll(selectionAlgorithmB.select(currentGeneration));

            List<Character> children = Crossing.randomCross(selectedParents, crossAlgorithm, crossingProb);

            selectedParents.clear();

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb());

            List<Character> newGen = replacementAlgorithm.newGeneration(children, currentGeneration,
                    selectionAlgorithmReplacementA,
                    selectionAlgorithmReplacementB);

            currentGeneration = newGen;
        }

    }
}
