package ar.edu.itba.sia.searchAlgorithms;

import java.util.List;

import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;


public class DepthFirstSearch<T> implements SearchAlgorithm<T> {

    @Override
    public void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes) {
        borderNodes.addAll(0, candidates);
    }


}