package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.ConfigurationManager;
import ar.edu.itba.sia.utils.Parser;
import ar.edu.itba.sia.utils.SelectionMethod;

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

        List<Character> selected = new LinkedList<>();

        while(true/*condicion*/) {
            selected.addAll(selectionAlgorithm1.select(initialGeneration));
            selected.addAll(selectionAlgorithm2.select(initialGeneration));
            //entre los dos obtenemos k candidatos.
            //
            //crossAlgorithm.cross(k candidatos);
            //salen k hijos.
            //
            //mutationAlgorithm.mute(k hijos);
            //salen los hijos mutados
            //
            //replacementAlgorithm1.newGeneration(k hijos, generation);
            //replacementAlgorithm2.newGeneration(k hijos, generation);
            //entre los dos da una nueva poblacion de long N.
            //
            selected.clear();
        }
    }
}
