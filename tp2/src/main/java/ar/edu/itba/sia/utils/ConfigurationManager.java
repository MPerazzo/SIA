package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.gridLock.structures.Board;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigurationManager {

    private final static String fileName = "caseMedium.txt";
    private final Parser parser;
    private final GridLockState initialState;

    private static final ConfigurationManager INSTANCE = new ConfigurationManager();

    private ConfigurationManager() {
        this.parser = new Parser();
        try {
            parser.parse(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("file " + fileName + " not found");
        } catch (IOException e) {
            System.out.println("failed to try to read the file: " + fileName);
        }

        Board board = new Board(parser.getPieces(), parser.getSize());
        initialState = new GridLockState(board, parser.getPieces());
    }

    public static ConfigurationManager getInstance() {
        return INSTANCE;
    }

    public GridLockState getInitialState() {
        return this.initialState;
    }
}
