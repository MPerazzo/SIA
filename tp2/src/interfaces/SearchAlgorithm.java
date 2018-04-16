package interfaces;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Problem;
import core.GenericNode;

public interface SearchAlgorithm<T> {
	void search(Problem<T> p, List<GenericNode<T>> candidates, 
			LinkedList<GenericNode<T>> borderNodes);
}
