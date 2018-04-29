package ar.edu.itba.sia.searchAlgorithms;

import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;

import java.util.List;


public class IterativeDeepeningSearch<T> implements SearchAlgorithm<T> {

    //Profundizacion iterativa mejorada es BFS (sin arrancar de cero)
    @Override
    public void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes) {
        borderNodes.addAll(candidates);
    }
}
