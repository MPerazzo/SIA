package ar.edu.itba.sia.core;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;

public class SearchEngine<T> {

    private LinkedList<GenericNode<T>> expandedNodes;
    private LinkedList<GenericNode<T>> borderNodes;

    public SearchEngine() {
        expandedNodes = new LinkedList<>();
        borderNodes = new LinkedList<>();
    }

    public void search(SearchAlgorithm<T> searchMethod, Problem<T> p) {
        Heuristic<T> defaultHeuristic = (t) -> 0;
        this.genericSearch(searchMethod, p, defaultHeuristic);
    }

    public void search(SearchAlgorithm<T> searchMethod, Problem<T> p, Heuristic<T> h) {
        this.genericSearch(searchMethod, p, h);
    }

    private void genericSearch(SearchAlgorithm<T> searchMethod, Problem<T> p, Heuristic<T> h) {

        T currentState = p.getInitialState();
        GenericNode<T> currentNode = new GenericNode<>(currentState);
        borderNodes.add(currentNode);

        while (!p.isResolved(currentState)) {

            currentNode = borderNodes.getFirst();
            currentState = currentNode.getState();

            List<Rule<T>> rulesToApply = p.getRules(currentState);

            borderNodes.removeFirst();
            expandedNodes.add(currentNode);

            // aplica las reglas
            List<GenericNode<T>> candidates = expand(rulesToApply, currentNode, h);

            searchMethod.search(p, candidates, borderNodes);
        }

    }

    public List<GenericNode<T>> expand(List<Rule<T>> toApply, GenericNode<T> currentNode,
                                       Heuristic<T> heuristic) {

        LinkedList<GenericNode<T>> candidates = new LinkedList<>();

        T currentState = currentNode.getState();

        for (Rule<T> r : toApply) {
            T newState = (T) r.applyToState(currentState);
            GenericNode<T> newNode = new GenericNode<T>(newState,
                    currentNode.getAccum() + r.getCost(), heuristic.getValue(newState),
                    r, currentNode);
            if (!borderNodes.contains(newNode) || !expandedNodes.contains(newNode))
            candidates.add(newNode);
        }
        return candidates;
    }
}
