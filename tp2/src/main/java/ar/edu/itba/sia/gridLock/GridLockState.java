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
                matrix[x_value + p.getType().isHorizontal() * j][y_value + p.getType().isVertical() * j] = i;
        }

        for (Integer[] array : matrix) {
            for (Integer value : array) {
                if (value == null)
                    s.append(" - ");
                else
                    s.append(" " + value + " ");
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
