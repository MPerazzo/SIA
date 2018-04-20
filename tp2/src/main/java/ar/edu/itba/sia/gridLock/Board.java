package ar.edu.itba.sia.gridLock;

/**
 * Created by matias on 18/04/18.
 */
public class Board {

    private final BitMatrix matrix;
    private final int size;

    public Board(Board board) {
        this.matrix = board.getMatrix().clone();
        this.size = board.getSize();
    }

    public Board(int size) {
        matrix = new BitMatrix(size, size);
        this.size = size;
    }

    public void movePiece(GridLockRule r) {

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

    public int getSize() {
        return size;
    }
}





