package ar.edu.itba.sia.core;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.interfaces.Node;

public class GenericNode<T> implements Node {
    private T state;
    private double accum;
    private double heuristicValue;
    private Rule<T> rule;
    private GenericNode<T> parent;

    public GenericNode(T initialState, Heuristic h) {
        state = initialState;
        accum = 0;
        heuristicValue = h.getValue(initialState);
    }

    public GenericNode(T state, double accum, double heuristic, Rule<T> rule,
                       GenericNode<T> parent) {
        this.state = state;
        this.accum = accum;
        this.heuristicValue = heuristic;
        this.rule = rule;
        this.parent = parent;
    }

    public GenericNode<T> getParent() {
        return parent;
    }

    @Override
    public T getState() {
        return state;
    }

    @Override
    public double getAccum() {
        return accum;
    }

    @Override
    public double getHeuristicValue() {
        return heuristicValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericNode<?> that = (GenericNode<?>) o;

        return this.getState().equals(that.getState());
    }

    @Override
    public int hashCode() {
        return this.getState().hashCode();
    }
}