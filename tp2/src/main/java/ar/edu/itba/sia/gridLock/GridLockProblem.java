package ar.edu.itba.sia.gridLock;

import java.util.LinkedList;
import java.util.List;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;

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
    public List<Rule<GridLockState>> getRules(GridLockState state) {

        List<Rule<GridLockState>> rules = new LinkedList<>();

        for(GridLockPiece p : state.getPieces()) {
            switch (p.getType()) {
                case VERTICAL:
                    rules.addAll(verticalMove(p,state.getBoard()));
                    break;
                case HORIZONTAL:
                    rules.addAll(horizontalMove(p,state.getBoard()));
            }
        }

      return rules;
    }

    @Override
    public boolean isResolved(GridLockState state) {

        GridLockPiece mainPiece = state.getMainPiece();

        return mainPiece.getPosition().getX() + mainPiece.getSize() == initialState.getBoard().getSize();
    }

    private List<Rule<GridLockState>> verticalMove(final GridLockPiece piece, final Board board) {

        List<Rule<GridLockState>> rules = new LinkedList<>();

        int pieceStart = piece.getPosition().getY();

        for(int offset = -1, i = 0 ; i < pieceStart ; i++, offset--) {
            if(!board.getMatrix().get(piece.getPosition().getX(),piece.getPosition().getY() + offset)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }

        }

        int boardHeight = board.getMatrix().getHeight()-1;

        for(int offset = 1, i = piece.getPosition().getY() + piece.getSize() ; i < boardHeight ; i++, offset ++) {
            if (!board.getMatrix().get(piece.getPosition().getX(),piece.getPosition().getY() + piece.getSize() + offset)) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }
        }

        return rules;
    }

    private List<Rule<GridLockState>> horizontalMove(final GridLockPiece piece, final Board board) {

        List<Rule<GridLockState>> rules = new LinkedList<>();

        int pieceStart = piece.getPosition().getX();

        for(int offset = -1, i = 0 ; i < pieceStart ; i++, offset--) {
            if(!board.getMatrix().get(piece.getPosition().getX() + offset, piece.getPosition().getY())) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }

        }

        int boardWidth = board.getMatrix().getWidth()-1;

        for(int offset = 1, i = piece.getPosition().getX() + piece.getSize() ; i < boardWidth ; i++, offset ++) {
            if (!board.getMatrix().get(piece.getPosition().getX() + piece.getSize() + offset, piece.getPosition().getY())) {
                rules.add(new GridLockRule(piece, offset));
            } else {
                break;
            }
        }

        return rules;
    }

}