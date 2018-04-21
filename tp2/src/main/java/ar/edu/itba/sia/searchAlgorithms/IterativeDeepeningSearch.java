package ar.edu.itba.sia.searchAlgorithms;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by matias on 20/04/18.
 */
public class IterativeDeepeningSearch<T> implements SearchAlgorithm<T> {

    //Profundizacion iterativa mejorada es BFS (sin arrancar de cero)
    @Override
    public void search(Problem<T> p, List<GenericNode<T>> candidates,
                       LinkedList<GenericNode<T>> borderNodes) {
        borderNodes.addAll(candidates);
    }
}
