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
                int pieceFinalY = pieceStartY + p.getSize() - 1;

                // both always positive
                int offsetA = mainPieceY - pieceStartY + 1;
                int offsetB = pieceFinalY - mainPieceY + 1;

                int topMiddlePieces = 0;
                if (pieceFinalY + offsetA < boardSize) {
                    for (int j = 1 ; j <= offsetA ; j++) {
                        if (!board.isEmpty(i, pieceFinalY + j))
                            topMiddlePieces++;
                    }
                }

                int bottomMiddlePieces = 0;
                if (pieceStartY - offsetB >= 0) {
                    for (int j = 1 ; j <= offsetB ; j++) {
                        if (!board.isEmpty(i, pieceStartY - j))
                            bottomMiddlePieces++;
                    }
                }

                int topMovements = topMiddlePieces + offsetA;
                int bottomMovements = bottomMiddlePieces + offsetB;

                if (topMovements > bottomMovements)
                    verticalMovements += topMovements;
                else
                    verticalMovements += bottomMovements;
            }
        }
        return distanceToGoal + verticalMovements;
    }
}