package ar.edu.itba.sia.searchAlgorithms;

import ar.edu.itba.sia.core.GenericNode;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;

import java.util.*;


public class IterativeDeepeningSearch<T> implements SearchAlgorithm<T> {

    private static final int HEIGHT_INCREMENT = 10;

    private Map<GenericNode<T>, Integer> nodesHeight;

    private int currentHeight = 0;
    private int finalHeight = HEIGHT_INCREMENT;

    public IterativeDeepeningSearch() {
        nodesHeight = new HashMap<>();
    }

    @Override
    public void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes) {

        if (nodesHeight.isEmpty()) {
            GenericNode<T> root = candidates.get(0).getParent();
            nodesHeight.put(root, 0);
        }

        for (GenericNode<T> n : candidates)
            nodesHeight.put(n, currentHeight++);

        if (currentHeight == finalHeight) {
            borderNodes.addAll(candidates);
            GenericNode<T> nextNodeToExplore = borderNodes.get(0);
            currentHeight = nodesHeight.get(nextNodeToExplore);

            boolean expandHeight = true;
            for (GenericNode<T> n : borderNodes) {
                GenericNode<T> parent = n.getParent();
                if (nodesHeight.get(parent) != finalHeight) {
                    expandHeight = false;
                    break;
                }
            }
            if (expandHeight)
                finalHeight += HEIGHT_INCREMENT;
        }
        else
            borderNodes.addAll(0, candidates);

    }
}
