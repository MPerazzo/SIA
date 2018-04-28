package ar.edu.itba.sia.gridLock.heuristics;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockState;

public class GridLockBasicHeuristic implements Heuristic<GridLockState> {

    @Override
    public double getValue(GridLockState currentState) {
        int boardSize = currentState.getBoard().getSize();
        GridLockPiece mainPiece = currentState.getMainPiece();
        return boardSize - (mainPiece.getPosition().getX() +  mainPiece.getSize());
    }
}
