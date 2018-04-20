package ar.edu.itba.sia.gridLock;


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
}
