package ar.edu.itba.sia.gridLock.structures;

import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockRule;
import ar.edu.itba.sia.utils.ConfigurationManager;

import java.util.List;

public class Board {

    private final BitMatrix matrix;
    private final int size;

    public Board(Board board, GridLockRule r) {
        this.matrix = board.getMatrix().clone();
        this.size = board.size;
        this.movePiece(r);
    }

    public Board(List<GridLockPiece> pieces, int size) {
        matrix = new BitMatrix(size, size);
        this.size = size;
        for(GridLockPiece p : pieces) {
            setPiece(p);
        }
    }

    private void setPiece(GridLockPiece p) {

        int size = p.getSize();
        int pieceStartY = p.getPosition().getY();
        int pieceStartX = p.getPosition().getX();

        switch (p.getType()) {

            case VERTICAL:

                for (int i = 0 ; i < size ; i++) {
                    matrix.set(pieceStartX, pieceStartY + i);
                }
                break;

            case HORIZONTAL:

                for (int i = 0 ; i < size ; i++) {
                    matrix.set(pieceStartX + i, pieceStartY);
                }
        }
    }

    private void movePiece(GridLockRule r) {

        GridLockPiece p = r.getPiece();
        int pieceStartX = p.getPosition().getX();
        int pieceStartY = p.getPosition().getY();
        int isHorizontal = p.getType().isHorizontal();
        int isVertical = p.getType().isVertical();
        int size = p.getSize();
        int offsetModule = (int) Math.abs(r.getOffset());
        int offsetSign = (int) Math.signum(r.getOffset());

        for(int i = 0 ; i < offsetModule && i < size ; i++) {
            if(offsetSign > 0) {
                matrix.unset(pieceStartX + isHorizontal * i,pieceStartY + isVertical * i);
            } else {
                matrix.unset(pieceStartX + isHorizontal * ((i * offsetSign) + (size-1))  , pieceStartY + isVertical * ((i * offsetSign) + (size-1)));
            }
        }
        if(offsetModule <= size) {
            for(int i = 0 ; i < offsetModule ; i++) {
                if(offsetSign > 0) {
                    matrix.set(pieceStartX + isHorizontal * (i + size), pieceStartY + isVertical * (i + size));
                } else {
                    matrix.set(pieceStartX + isHorizontal * (i * offsetSign - 1), pieceStartY + isVertical * (i * offsetSign -1));
                }
            }
        } else {
            for(int i = 0 ; i < size ; i++) {
                if(offsetSign > 0) {
                    matrix.set(pieceStartX + isHorizontal * (i + offsetModule), pieceStartY + isVertical * (i + offsetModule));
                } else {
                    matrix.set(pieceStartX + isHorizontal * (i * offsetSign - (offsetModule-1)), pieceStartY + isVertical * (i * offsetSign -(offsetModule-1)));
                }
            }
        }


    }

    public boolean isEmpty(int x, int y) {
        return !this.matrix.get(x, y);
    }

    public BitMatrix getMatrix() {
        return matrix;
    }

    public int getSize() { return size;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board that = (Board) o;

        return this.matrix.equals(that.getMatrix());

    }

    @Override
    public int hashCode() {
        return this.matrix.hashCode();
    }
}





