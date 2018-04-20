package ar.edu.itba.sia.gridLock;

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
      return null;
    }

    @Override
    public boolean isResolved(GridLockState state) {

        GridLockPiece mainPiece = state.getMainPiece();

        return mainPiece.getPosition().getX() + mainPiece.getSize() == initialState.getBoard().getSize();
    }


}