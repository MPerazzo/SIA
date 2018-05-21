package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.Parser;
import ar.edu.itba.sia.utils.SelectionMethod;

import java.util.List;

public class GeneticAlgorithm {

    public GeneticAlgorithm(){

    }

    public void geneticAlgorithm(Parser parser, List<Character> generation) {
        double selectionPercent = parser.getSelectionPercent();
        double replacementPercent = parser.getReplacementPercent();
        int population = parser.getPopulation();
        int k = parser.getSelectionCant();
        int k1 = (int)Math.floor(selectionPercent*k);
        int k2 = k - k1;
        int N1 = (int)Math.floor(replacementPercent*population);
        int N2 = population-N1;

        //incluir k en los constructores de los algoritmos de seleccion.
        //Para todos los algoritmos de sel k es la cantidad de padres que va a devolver.
        //Esto se hace para generalizar y ademas para que funcione lo de k1 y k2.
        SelectionAlgorithm selectionAlgorithm1 = SelectionMethod.getSelectionAlgorithm(parser.getFirstSelectionMethod(), parser);
        SelectionAlgorithm selectionAlgorithm2 = SelectionMethod.getSelectionAlgorithm(parser.getFirstSelectionMethod(), parser);
        //la misma logica de inicializacion para los demas algoritmos de cruza, mutacion, reemplazo.
        //
        //Para reemplazo ademas de pasar Ni,
        //se va a pasar tambien un parser el cual va a servir para saber que algoritmo se seleccion va a usar para la nueva pob.

        while(true/*condicion*/) {
            //selectionAlgorithm1.select(generation);
            //selectionAlgorithm2.select(generation);
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
        }
    }
}
