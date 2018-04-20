package ar.edu.itba.sia.gridLock;


import ar.edu.itba.sia.gridLock.structures.Position2D;

public class GridLockPiece {

    private Position2D position;
    private int size;
    private GridLockPieceType type;

    public GridLockPiece(GridLockPiece p, int offset) {
        this.size = p.getSize();
        this.type = p.getType();
        this.position = new Position2D(p.getPosition().getX() +  type.isHorizontal() * offset,
                p.getPosition().getY() + type.isVertical() * offset);
    }

    public GridLockPiece(Position2D position, int size, GridLockPieceType type) {
        this.position = position;
        this.size = size;
        this.type = type;
    }

    public GridLockPieceType getType() {
        return type;
    }

    public Position2D getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridLockPiece other = (GridLockPiece) o;

        return this.position.equals(other.position);
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result;
        return result;
    }
}
