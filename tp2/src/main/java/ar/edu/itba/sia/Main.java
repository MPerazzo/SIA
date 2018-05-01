package ar.edu.itba.sia;


import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.chainreaction.problem.ChainReactionNeighbourPruningHeuristic;
import ar.edu.itba.sia.chainreaction.problem.ChainReactionState;
import ar.edu.itba.sia.chainreaction.problem.ProblemFactory;
import ar.edu.itba.sia.core.SearchEngine;
import ar.edu.itba.sia.gridLock.heuristics.*;
import ar.edu.itba.sia.interfaces.InformedSearchAlgorithm;
import ar.edu.itba.sia.interfaces.Node;
import ar.edu.itba.sia.interfaces.UnInformedSearchAlgorithm;
import ar.edu.itba.sia.searchAlgorithms.*;
import ar.edu.itba.sia.gridLock.GridLockProblem;
import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.utils.ConfigurationManager;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

//        SearchEngine<GridLockState> searchEngine = new SearchEngine<>();
//
//        GridLockProblem p = new GridLockProblem(ConfigurationManager.getInstance().getInitialState());
//
//        UnInformedSearchAlgorithm<GridLockState> unInformedSearchAlgorithm = new BreadthFirstSearch<>();
//
//        InformedSearchAlgorithm<GridLockState> informedSearchAlgorithm = new AStarSearch<>();
//
//        Heuristic<GridLockState> heuristic = new GridLockBasicHeuristic();

        //searchEngine.search(unInformedSearchAlgorithm, p);

        //searchEngine.search(informedSearchAlgorithm, p, heuristic);

        /*



         */
        File problemFile = new File("pjf7.txt");

        try {
            Problem p = ProblemFactory.createChainReactionProblem(problemFile);
            Heuristic<ChainReactionState> h1 = new ChainReactionNeighbourPruningHeuristic();
            SearchEngine<ChainReactionState> searchEngine = new SearchEngine<>();
            InformedSearchAlgorithm<ChainReactionState> informedSearchAlgorithm = new AStarSearch<>();
            searchEngine.search(informedSearchAlgorithm, p, h1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}