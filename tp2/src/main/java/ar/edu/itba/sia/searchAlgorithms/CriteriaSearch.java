package ar.edu.itba.sia.searchAlgorithms;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.core.GenericNode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by matias on 21/04/18.
 */
public class CriteriaSearch<T> {

    public void search(Problem<T> p, List<GenericNode<T>> candidates,
                       List<GenericNode<T>> borderNodes, Comparator<GenericNode<T>> criteria) {

        for (int i=0 ; i < borderNodes.size() ; i++) {
            for (Iterator<GenericNode<T>> iterator = candidates.iterator(); iterator.hasNext();) {
                GenericNode<T> candidate = iterator.next();
                if (criteria.compare(borderNodes.get(i), candidate) >= 0) {
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
