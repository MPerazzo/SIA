package ar.edu.itba.sia;


import ar.edu.itba.sia.core.SearchEngine;
import ar.edu.itba.sia.gridLock.heuristics.*;
import ar.edu.itba.sia.searchAlgorithms.*;
import ar.edu.itba.sia.gridLock.GridLockProblem;
import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.utils.ConfigurationManager;

public class Main {

    public static void main(String[] args) {

        SearchEngine<GridLockState> searchEngine = new SearchEngine<>();

        GridLockProblem p = new GridLockProblem(ConfigurationManager.getInstance().getInitialState());

        searchEngine.search(new IterativeDeepeningSearch<>(), p);

    }

}