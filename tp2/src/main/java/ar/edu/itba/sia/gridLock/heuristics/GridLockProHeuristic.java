package ar.edu.itba.sia.gridLock.heuristics;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.gridLock.structures.Board;

public class GridLockProHeuristic implements Heuristic<GridLockState> {
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
        Board board = currentState.getBoard();

        int verticalMovements = 0;
        for (int i = mainPieceFinalPositionX; i < boardSize; i++) {
            if (!board.isEmpty(i, mainPieceY)) {
                GridLockPiece p = currentState.getPiece(i, mainPieceY);
                int pieceStartY = p.getPosition().getY();
                int pieceFinalY = pieceStartY + p.getSize();

                // both always positive
                int offsetA = mainPieceY - pieceStartY + 1;
                int offsetB = pieceFinalY - mainPieceY + 1;

                if (mainPieceY + offsetA < boardSize && board.isEmpty(i, mainPieceY + offsetA) && offsetA < offsetB)
                    verticalMovements += offsetA;
                else if (mainPieceY - offsetB > 0 && board.isEmpty(i, mainPieceY - offsetB))
                    verticalMovements += offsetB;
                else if (mainPieceY + offsetA < boardSize && board.isEmpty(i, mainPieceY + offsetA))
                    verticalMovements += offsetA;
            }
        }
        return distanceToGoal + verticalMovements;
    }
}