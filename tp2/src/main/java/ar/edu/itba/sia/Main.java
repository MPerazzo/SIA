package ar.edu.itba.sia;


import ar.edu.itba.sia.core.SearchEngine;
import ar.edu.itba.sia.gridLock.GridLockBasicHeuristic;
import ar.edu.itba.sia.gridLock.GridLockProblem;
import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.interfaces.SearchAlgorithm;
import ar.edu.itba.sia.searchAlgorithms.AStarSearch;
import ar.edu.itba.sia.utils.ConfigurationManager;

public class Main {

    public static void main(String[] args) {

        SearchEngine<GridLockState> searchEngine = new SearchEngine<>();

        SearchAlgorithm<GridLockState> searchAlgorithm = new AStarSearch<>();

        GridLockProblem p = new GridLockProblem(ConfigurationManager.getInstance().getInitialState());

        searchEngine.search(searchAlgorithm, p, new GridLockBasicHeuristic());

    }

}