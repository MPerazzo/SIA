package ar.edu.itba.sia.searchAlgorithms;

import java.util.List;

import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.UnInformedSearchAlgorithm;


public class BreadthFirstSearch<T> implements UnInformedSearchAlgorithm<T> {

    @Override
    public void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes) {
        borderNodes.addAll(candidates);
    }


}