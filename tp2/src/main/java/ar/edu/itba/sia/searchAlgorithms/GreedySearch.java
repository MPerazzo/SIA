package ar.edu.itba.sia.searchAlgorithms;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by matias on 20/04/18.
 */
public class GreedySearch<T> implements SearchAlgorithm<T> {
    @Override
    public void search(Problem<T> p, List<GenericNode<T>> candidates,
                       List<GenericNode<T>> borderNodes) {

        Comparator<GenericNode> comparator = (n1, n2) -> ((int) n1.getHeuristicValue()
                - (int) n2.getHeuristicValue());

        for (int i=0 ; i < borderNodes.size() ; i++) {
            for (Iterator<GenericNode<T>> iterator = candidates.iterator(); iterator.hasNext();) {
                GenericNode<T> candidate = iterator.next();
                if (comparator.compare(borderNodes.get(i), candidate) >= 0) {
                    borderNodes.add(i, candidate);
                    iterator.remove();
                    i++;
                }
            }
            if (candidates.isEmpty())
                break;
        }

        if (!candidates.isEmpty())
            borderNodes.addAll(candidates);
    }
}
