package ar.edu.itba.sia.gridLock;

import java.util.*;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.gridLock.structures.Board;

public class GridLockProblem implements Problem<GridLockState> {

    private GridLockState initialState;

    private Map<Integer, Map<GridLockPiece, Map<Integer, GridLockRule>>> allRules;

    public GridLockProblem(GridLockState initialState) {
        this.initialState = initialState;
        this.allRules = new HashMap<>();
        generateAllRules();
    }

    private void generateAllRules() {

        Set<GridLockState> allStates = new HashSet<>();
        allStates.add(initialState);

        List<GridLockState> states = new LinkedList<>();
        states.add(initialState);

        while (!states.isEmpty()) {
            GridLockState currentState = states.get(0);
            List<GridLockPiece> pieces = currentState.getPieces();
            for(int i = 0 ; i < pieces.size() ; i++) {
                GridLockPiece p = pieces.get(i);
                switch (p.getType()) {
                    case VERTICAL:
                        verticalMoveGen(p, currentState, states, allStates, i);
                        break;
                    case HORIZONTAL:
                        horizontalMoveGen(p, currentState, states, allStates, i);
                }
            }
            states.remove(0);
        }
    }

    @Override
    public GridLockState getInitialState() {
        return initialState;
    }

    @Override
    public boolean isResolved(GridLockState state) {

        System.out.println(state);

        GridLockPiece mainPiece = state.getMainPiece();

        return mainPiece.getPosition().getX() + mainPiece.getSize() == initialState.getBoard().getSize();
    }

    @Override
    public List<Rule<GridLockState>> getRules(GridLockState state) {

        List<Rule<GridLockState>> rules = new LinkedList<>();

        List<GridLockPiece> pieces = state.getPieces();
        for(int i = 0 ; i < pieces.size() ; i++) {
            GridLockPiece p = pieces.get(i);
            switch (p.getType()) {
                case VERTICAL:
                    verticalMove(p, state.getBoard(), rules, i);
                    break;
                case HORIZONTAL:
                    horizontalMove(p, state.getBoard(), rules, i);
            }
        }

        return rules;
    }


    private void verticalMove(final GridLockPiece piece, final Board board,
                              List<Rule<GridLockState>> rules, int index) {

        int pieceStartX = piece.getPosition().getX();
        int pieceStartY = piece.getPosition().getY();
        int pieceSize = piece.getSize();

        for(int offset = -1, i = 0 ; i < pieceStartY ; i++, offset--) {
            if(board.isEmpty(pieceStartX, pieceStartY + offset)) {
                rules.add(allRules.get(index).get(piece).get(offset));
            } else {
                break;
            }
        }

        int boardHeight = board.getSize() - 1;

        for(int offset = 1, i = pieceStartY + (pieceSize - 1) ; i < boardHeight ; i++, offset ++) {
            if (board.isEmpty(pieceStartX, pieceStartY + (pieceSize - 1) + offset)) {
                rules.add(allRules.get(index).get(piece).get(offset));
            } else {
                break;
            }
        }
    }

    private void horizontalMove(final GridLockPiece piece, final Board board,
                                                     List<Rule<GridLockState>> rules, int index) {

        int pieceStartX = piece.getPosition().getX();
        int pieceStartY = piece.getPosition().getY();
        int pieceSize = piece.getSize();

        for(int offset = -1, i = 0 ; i < pieceStartX ; i++, offset--) {
            if(board.isEmpty(pieceStartX + offset, pieceStartY)) {
                rules.add(allRules.get(index).get(piece).get(offset));
            } else {
                break;
            }

        }

        int boardWidth = board.getSize() - 1;

        for(int offset = 1, i = pieceStartX + (pieceSize - 1) ; i < boardWidth ; i++, offset ++) {
            if (board.isEmpty(pieceStartX + (pieceSize - 1) + offset, pieceStartY)) {
                rules.add(allRules.get(index).get(piece).get(offset));
            } else {
                break;
            }
        }
    }

    private void verticalMoveGen(final GridLockPiece piece, final GridLockState currentState
            , List<GridLockState> states, final Set<GridLockState> allStates, final int index) {

        //     private Map<Integer, Map<GridLockPiece, Map<Integer, GridLockRule>>> allRules;

        Board board = currentState.getBoard();

        int pieceStartX = piece.getPosition().getX();
        int pieceStartY = piece.getPosition().getY();
        int pieceSize = piece.getSize();

        if (!allRules.containsKey(index))
            allRules.put(index, new HashMap<>());

        if (!allRules.get(index).containsKey(piece))
            allRules.get(index).put(piece, new HashMap<>());

        Map<Integer, GridLockRule> rules = allRules.get(index).get(piece);

        for(int offset = -1, i = 0 ; i < pieceStartY ; i++, offset--) {
            if(board.isEmpty(pieceStartX, pieceStartY + offset)) {
                GridLockRule newRule = new GridLockRule(piece, offset);
                rules.put(offset, newRule);
                GridLockState newState = newRule.applyToState(currentState);
                if (!allStates.contains(newState)) {
                    states.add(newState);
                    allStates.add(newState);
                }
            } else {
                break;
            }
        }

        int boardHeight = board.getSize() - 1;

        for(int offset = 1, i = pieceStartY + (pieceSize - 1) ; i < boardHeight ; i++, offset ++) {
            if (board.isEmpty(pieceStartX, pieceStartY + (pieceSize - 1) + offset)) {
                GridLockRule newRule = new GridLockRule(piece, offset);
                rules.put(offset, newRule);
                GridLockState newState = newRule.applyToState(currentState);
                if (!allStates.contains(newState)) {
                    states.add(newState);
                    allStates.add(newState);
                }
            } else {
                break;
            }
        }
    }

    private void horizontalMoveGen(final GridLockPiece piece, final GridLockState currentState
            , List<GridLockState> states, final Set<GridLockState> allStates, final int index) {

        Board board = currentState.getBoard();

        int pieceStartX = piece.getPosition().getX();
        int pieceStartY = piece.getPosition().getY();
        int pieceSize = piece.getSize();

        if (!allRules.containsKey(index))
            allRules.put(index, new HashMap<>());

        if (!allRules.get(index).containsKey(piece))
            allRules.get(index).put(piece, new HashMap<>());

        Map<Integer, GridLockRule> rules = allRules.get(index).get(piece);

        for(int offset = -1, i = 0 ; i < pieceStartX ; i++, offset--) {
            if(board.isEmpty(pieceStartX + offset, pieceStartY)) {
                GridLockRule newRule = new GridLockRule(piece, offset);
                rules.put(offset, newRule);
                GridLockState newState = newRule.applyToState(currentState);
                if (!allStates.contains(newState)) {
                    states.add(newState);
                    allStates.add(newState);
                }
            } else {
                break;
            }

        }

        int boardWidth = board.getSize() - 1;

        for(int offset = 1, i = pieceStartX + (pieceSize - 1) ; i < boardWidth ; i++, offset ++) {
            if (board.isEmpty(pieceStartX + (pieceSize - 1) + offset, pieceStartY)) {
                GridLockRule newRule = new GridLockRule(piece, offset);
                rules.put(offset, newRule);
                GridLockState newState = newRule.applyToState(currentState);
                if (!allStates.contains(newState)) {
                    states.add(newState);
                    allStates.add(newState);
                }
            } else {
                break;
            }
        }
    }
}