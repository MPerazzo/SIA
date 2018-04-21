package ar.edu.itba.sia.gridLock;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.gridLock.structures.Board;

public class GridLockProblem implements Problem<GridLockState> {

    private GridLockState initialState;

    public GridLockProblem(GridLockState initialState) {
        this.initialState = initialState;
    }

    @Override
    public GridLockState getInitialState() {
        return initialState;
    }

    @Override
    public boolean isResolved(GridLockState state) {

        GridLockPiece mainPiece = state.getMainPiece();

        return mainPiece.getPosition().getX() + mainPiece.getSize() == initialState.getBoard().getSize()-1;
    }

    @Override
    public List<Rule<GridLockState>> getRules(GridLockState state) {

        List<Rule<GridLockState>> rules = new LinkedList<>();

        for(GridLockPiece p : state.getPieces()) {
            switch (p.getType()) {
                case VERTICAL:
                    verticalMove(p, state.getBoard(), rules);
                    break;
                case HORIZONTAL:
                    horizontalMove(p, state.getBoard(), rules);
            }
        }

        return rules;
    }


    private void verticalMove(final GridLockPiece piece, final Board board,
                              List<Rule<GridLockState>> rules) {

        int pieceStartX = piece.getPosition().getX();
        int pieceStartY = piece.getPosition().getY();
        int pieceSize = piece.getSize();

        for(int offset = -1, i = 0 ; i < pieceStartY ; i++, offset--) {
            if(!board.getMatrix().get(pieceStartX, pieceStartY + offset)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }

        }

        int boardHeight = board.getSize() - 1;

        for(int offset = 1, i = pieceStartY + pieceSize ; i < boardHeight ; i++, offset ++) {
            if (!board.getMatrix().get(pieceStartX, pieceStartY + pieceSize + offset)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }
        }
    }

    private void horizontalMove(final GridLockPiece piece, final Board board,
                                                     List<Rule<GridLockState>> rules) {

        int pieceStartX = piece.getPosition().getX();
        int pieceStartY = piece.getPosition().getY();
        int pieceSize = piece.getSize();

        for(int offset = -1, i = 0 ; i < pieceStartX ; i++, offset--) {
            if(!board.getMatrix().get(pieceStartX + offset, pieceStartY)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }

        }

        int boardWidth = board.getSize() - 1;

        for(int offset = 1, i = pieceStartX + pieceSize ; i < boardWidth ; i++, offset ++) {
            if (!board.getMatrix().get(pieceStartX + pieceSize + offset, pieceStartY)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }
        }
    }

}