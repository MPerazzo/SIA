package core;

import ar.com.itba.sia.Rule;
import interfaces.Node;

public class GenericNode<T> implements Node {
	private T state;
	private double accum;
	private double heuristicValue;
	private Rule<T> rule;
	private GenericNode<T> parent;
	
	public GenericNode(T initialState) {
		state = initialState;
		accum = 0;
		heuristicValue = 0;
	}

	public GenericNode(T state, double accum, double heuristic, Rule<T> rule,
			GenericNode<T> parent) {
		this.state = state;
		this.accum = accum;
		this.heuristicValue = heuristic;
		this.rule = rule;
		this.parent = parent;
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
}
