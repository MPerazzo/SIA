package ar.edu.itba.sia.gridLock;

/**
 * Created by matias on 18/04/18.
 */
public enum GridLockPieceType {
    VERTICAL(0),
    HORIZONTAL(1);

    private int type;

    private GridLockPieceType(int type) {
        this.type = type;
    }

    public int isVertical() {
        if (type == 0)
            return 1;
        return 0;
    }

    public int isHorizontal() {
        if (type == 1)
            return 1;
        return 0;
    }
}
