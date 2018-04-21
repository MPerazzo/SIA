package ar.edu.itba.sia.searchAlgorithms;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by matias on 20/04/18.
 */
public class GreedySearch<T> implements SearchAlgorithm<T> {
    @Override
    public void search(Problem<T> p, List<GenericNode<T>> candidates,
                       List<GenericNode<T>> borderNodes) {
        //border nodes ya esta ordenado
        Comparator<GenericNode> comparator = (n1, n2) -> ((int) n1.getHeuristicValue()
                - (int) n2.getHeuristicValue());

        int i = 0;
        for (GenericNode borderNode : borderNodes) {
            int j = 0;
            for (GenericNode candidate : candidates) {
                if (comparator.compare(borderNode, candidate) >= 0) {
                    borderNodes.add(i, candidate);
                    //remuevo por índice para que no recorra toda la lista
                    candidates.remove(j);
                }
                else
                    j++;
            }
            if (borderNodes.isEmpty())
                break;

            i++;
        }

        if (!candidates.isEmpty())
            borderNodes.addAll(candidates);
    }
}
