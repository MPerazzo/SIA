package ar.edu.itba.sia.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.interfaces.InformedSearchAlgorithm;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;
import ar.edu.itba.sia.interfaces.UnInformedSearchAlgorithm;
import ar.edu.itba.sia.utils.Metrics;

public class SearchEngine<T> {

    private List<GenericNode<T>> borderNodes;
    private Set<GenericNode<T>> allNodes;

    private Metrics<T> metricGenerator = Metrics.getInstance();

    public SearchEngine() {
        borderNodes = new LinkedList<>();
        allNodes = new HashSet<>();
    }

    public void search(UnInformedSearchAlgorithm<T> searchMethod, Problem<T> p) {
        Heuristic<T> defaultHeuristic = (t) -> 0;
        this.genericSearch(searchMethod, p, defaultHeuristic);
    }

    public void search(InformedSearchAlgorithm<T> searchMethod, Problem<T> p, Heuristic<T> h) {
        this.genericSearch(searchMethod, p, h);
    }

    private void genericSearch(SearchAlgorithm<T> searchMethod, Problem<T> p, Heuristic<T> h) {

        T currentState = p.getInitialState();
        GenericNode<T> currentNode = new GenericNode<>(currentState, h);
        borderNodes.add(currentNode);
        allNodes.add(currentNode);

        try {
            while (!p.isResolved(currentState)) {
                List<Rule<T>> rulesToApply = p.getRules(currentState);

                borderNodes.remove(0);

                List<GenericNode<T>> candidates = expand(rulesToApply, currentNode, h);

                searchMethod.search(candidates, borderNodes);

                currentNode = borderNodes.get(0);
                currentState = currentNode.getState();
            }
            metricGenerator.computeMetrics(allNodes.size(), borderNodes.size(), currentNode);
        }

        catch (IndexOutOfBoundsException e) {
            System.out.println("El estado inicial no tiene solución");
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
            if (!allNodes.contains(newNode)) {
                candidates.add(newNode);
                allNodes.add(newNode);
            }
            else
                metricGenerator.repHit();
        }
        return candidates;
    }
}
