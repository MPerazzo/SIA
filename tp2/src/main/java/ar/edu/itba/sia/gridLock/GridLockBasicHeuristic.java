package ar.edu.itba.sia.gridLock;

import ar.com.itba.sia.Heuristic;

public class GridLockBasicHeuristic implements Heuristic<GridLockState> {

    @Override
    public double getValue(GridLockState currentState) {
        int boardSize = currentState.getBoard().getSize();
        GridLockPiece mainPiece = currentState.getMainPiece();
        return boardSize - (mainPiece.getPosition().getX() +  mainPiece.getSize());
    }
}
