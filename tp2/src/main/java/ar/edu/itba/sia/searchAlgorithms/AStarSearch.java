package ar.edu.itba.sia.searchAlgorithms;

import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.InformedSearchAlgorithm;

import java.util.Comparator;
import java.util.List;


public class AStarSearch<T> implements InformedSearchAlgorithm<T> {
    @Override
    public void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes) {

        Comparator<GenericNode<T>> comparator = (n1, n2) -> (((int) n1.getHeuristicValue() + (int) n1.getAccum())
                - ((int) n2.getHeuristicValue() + (int) n2.getAccum()));

        new CriteriaSearch<T>().search(candidates, borderNodes, comparator);
    }
}
