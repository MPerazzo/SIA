package ar.edu.itba.sia.gridLock.heuristics;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.gridLock.structures.Board;

public class GridLockAdvancedHeuristic implements Heuristic<GridLockState> {
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
        for (int i = mainPieceFinalPositionX ; i < boardSize ; i++) {
            if(!board.isEmpty(i, mainPieceY)) {
                GridLockPiece p = currentState.getPiece(i, mainPieceY);
                int pieceStartY = p.getPosition().getY();
                int pieceFinalY = pieceStartY + p.getSize();
                //worst case and best case movements
                double movements = Math.abs(pieceStartY - mainPieceY) + 1 + Math.abs(pieceFinalY - mainPieceY) + 1;
                //average case movements
                verticalMovements += (int) Math.ceil(movements/2);
            }
        }
        return distanceToGoal + verticalMovements;
    }
}
