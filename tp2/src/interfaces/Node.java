package interfaces;

public interface Node<T> {
	T getState();
	double getAccum();
	double getHeuristicValue();
}
