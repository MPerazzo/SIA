package ar.edu.itba.sia.gridLock;


import ar.edu.itba.sia.gridLock.structures.Board;
import ar.edu.itba.sia.interfaces.State;

import java.util.LinkedList;
import java.util.List;

//Immutable
public class GridLockState implements State {

    private final Board board;

    private List<GridLockPiece> pieces = new LinkedList<GridLockPiece>();


    public GridLockState(Board board, List<GridLockPiece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    public Board getBoard() {
        return board;
    }

    public List<GridLockPiece> getPieces() {
        return pieces;
    }

    public GridLockPiece getMainPiece() {
        return pieces.get(0);
    }

    public GridLockPiece getPiece(int x, int y) {

        for (GridLockPiece p : pieces) {
            int pieceX = p.getPosition().getX();
            int pieceY = p.getPosition().getY();
            int pieceSize = p.getSize() - 1;
            GridLockPieceType pieceOrientation = p.getType();

            if (x >= pieceX && x <= pieceX + pieceSize * pieceOrientation.isHorizontal()
                    && y >= pieceY && y <= pieceY + pieceSize * pieceOrientation.isVertical())
                return p;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        int size = board.getSize();
        Integer[][] matrix = new Integer[size][size];

        for (int i = 0 ; i < pieces.size() ; i++) {
            GridLockPiece p = pieces.get(i);
            int x_value = p.getPosition().getX();
            int y_value = p.getPosition().getY();
            int pieceSize = p.getSize();

            for (int j = 0 ; j < pieceSize ; j++)
                matrix[y_value + p.getType().isVertical() * j][x_value + p.getType().isHorizontal() * j] = i + 1;
        }

        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (matrix[i][j] == null)
                    s.append(" - ");
                else
                    s.append(" " + matrix[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridLockState that = (GridLockState) o;

        return pieces.equals(that.pieces);
    }

    @Override
    public int hashCode() {
        return pieces.hashCode();
    }
}
