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

        return mainPiece.getPosition().getX() + mainPiece.getSize() == initialState.getBoard().getSize();
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

        for(int offset = -1, i = 0 ; i < pieceStartY ; i++, offset--) {
            if(!board.getMatrix().get(pieceStartX, pieceStartY + offset)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }

        }

        int boardHeight = board.getSize() - 1;

        for(int offset = 1, i = pieceStartY + piece.getSize() ; i < boardHeight ; i++, offset ++) {
            if (!board.getMatrix().get(pieceStartX, pieceStartY + piece.getSize() + offset)) {
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

        for(int offset = -1, i = 0 ; i < pieceStartX ; i++, offset--) {
            if(!board.getMatrix().get(pieceStartX + offset, pieceStartY)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }

        }

        int boardWidth = board.getSize() - 1;

        for(int offset = 1, i = pieceStartX + piece.getSize() ; i < boardWidth ; i++, offset ++) {
            if (!board.getMatrix().get(pieceStartX + piece.getSize() + offset, pieceStartY)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }
        }
    }

}