package ar.edu.itba.sia.interfaces;


public interface Node<T> {
    T getState();
    double getAccum();
    double getHeuristicValue();
}
