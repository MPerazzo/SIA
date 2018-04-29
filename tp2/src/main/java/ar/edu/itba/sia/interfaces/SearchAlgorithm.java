package ar.edu.itba.sia.interfaces;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.core.GenericNode;

public interface SearchAlgorithm<T> {
    void search(List<GenericNode<T>> candidates, List<GenericNode<T>> borderNodes);
}