package ar.edu.itba.sia.gridLock.heuristics;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.gridLock.structures.BitMatrix;
import ar.edu.itba.sia.gridLock.structures.Board;

public class GridLockMediumHeuristic implements Heuristic<GridLockState> {

    @Override
    public double getValue(GridLockState currentState) {

        Heuristic<GridLockState> basicHeuristic = new GridLockBasicHeuristic();

        int distanceToGoal = (int) basicHeuristic.getValue(currentState);

        int boardSize = currentState.getBoard().getSize();
        GridLockPiece mainPiece = currentState.getMainPiece();
        int mainPieceX = mainPiece.getPosition().getX();
        int mainPieceY = mainPiece.getPosition().getY();
        int mainPieceSize = mainPiece.getSize();
        int mainPieceFinalPositionX = mainPieceX + mainPieceSize;
        BitMatrix matrix = currentState.getBoard().getMatrix();

        int middlePieces = 0;
        for (int i = mainPieceFinalPositionX ; i < boardSize ; i++) {
            if(matrix.get(i, mainPieceY))
                middlePieces++;
        }
        return distanceToGoal + middlePieces;
    }
}
