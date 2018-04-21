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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridLockState that = (GridLockState) o;

        return this.getBoard().equals(that.getBoard());
    }

    @Override
    public int hashCode() {
        return this.getBoard().hashCode();
    }
}
