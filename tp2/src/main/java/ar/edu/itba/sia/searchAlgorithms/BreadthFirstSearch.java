package ar.edu.itba.sia.searchAlgorithms;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;


public class BreadthFirstSearch<T> implements SearchAlgorithm<T> {

    @Override
    public void search(Problem<T> p, List<GenericNode<T>> candidates,
                       LinkedList<GenericNode<T>> borderNodes) {
        borderNodes.addAll(candidates);
    }


}