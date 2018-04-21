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

                for (int i = pieceStartY ; i <= size ; i++) {
                    matrix.set(pieceStartX,i);
                }
                break;

            case HORIZONTAL:

                for (int i = pieceStartX ; i <= size ; i++) {
                    matrix.set(i, pieceStartY);
                }
        }
    }

    private void movePiece(GridLockRule r) {

        GridLockPiece p = r.getPiece();
        int size = p.getSize();
        int offset = (int) r.getOffset();
        int offsetSign = (int) Math.signum(offset);

        for (int i = 0; i < offset; i = i + offsetSign) {
            matrix.unset(p.getPosition().getX() + p.getType().isHorizontal() * i,
                    p.getPosition().getY() + p.getType().isVertical() * i);
        }

        for (int i = size; i < size + offset; i = i + offsetSign) {
            matrix.set(p.getPosition().getX() + p.getType().isHorizontal() * i,
                    p.getPosition().getY() + p.getType().isVertical() * i);
        }
    }

    public BitMatrix getMatrix() {
        return matrix;
    }

    public int getSize() { return size;}
}





