package ar.edu.itba.sia.gridLock;

import ar.com.itba.sia.Heuristic;

public class GridLockHeuristic implements Heuristic<GridLockState> {

    @Override
    public double getValue(GridLockState currentState) {
        int size = currentState.getBoard().getSize();
        return size - (currentState.getMainPiece().getPosition().getX() +  currentState.getMainPiece().getSize());
    }
}
