package searchAlgorithms;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Problem;
import core.GenericNode;
import interfaces.SearchAlgorithm;

public class DepthFirstSearch<T> implements SearchAlgorithm<T>{

	@Override
	public void search(Problem<T> p, List<GenericNode<T>> candidates,
			LinkedList<GenericNode<T>> borderNodes) {
		borderNodes.addAll(1, candidates);
	}


}
