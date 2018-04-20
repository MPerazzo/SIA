package ar.edu.itba.sia.gridLock;

import ar.com.itba.sia.Rule;

import java.util.LinkedList;
import java.util.List;

public class GridLockRule implements Rule<GridLockState> {

    private final GridLockPiece piece;
    private double offset;

    public GridLockRule(GridLockPiece piece, double offset) {
        this.piece = piece;
        this.offset = offset;
    }

    public GridLockPiece getPiece() {
        return piece;
    }

    public double getOffset() { return offset; }

    @Override
    public double getCost() {
        return Math.abs(offset);
    }

    @Override
    public void setCost(double cost) {
        this.offset = cost;
    }

    @Override
    public GridLockState applyToState(GridLockState currentState) {

        List<GridLockPiece> newPieces = new LinkedList<>(currentState.getPieces());

        Board newBoard = new Board(currentState.getBoard(), this);

        GridLockPiece newPiece = new GridLockPiece(this.piece, (int) this.offset);

        newPieces.remove(this.piece);

        if (currentState.getMainPiece().equals(this.getPiece()))
            newPieces.add(0, newPiece);
        else
            newPieces.add(newPiece);

        return new GridLockState(newBoard, newPieces);

    }
}
