package ar.edu.itba.sia.searchAlgorithms;

import ar.edu.itba.sia.core.GenericNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CriteriaSearch<T> {

    public void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes,
                       Comparator<GenericNode<T>> criteria) {

        Collections.sort(candidates, criteria);

        for (int i=0 ; i < borderNodes.size() && !candidates.isEmpty() ; i++) {
            GenericNode<T> candidate =  candidates.get(0);
            if (criteria.compare(borderNodes.get(i), candidate) >= 0) {
                borderNodes.add(i, candidate);
                candidates.remove(0);
            }

        }
        borderNodes.addAll(candidates);
    }
}
