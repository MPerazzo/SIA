package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.gridLock.structures.Board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ConfigurationManager {

    private final static String fileName = "configFile.txt";
    private final Parser parser;
    private final GridLockState initialState;

    private static ConfigurationManager intance = null;

    private ConfigurationManager() {
        this.parser = new Parser();
        try {
            parser.parse(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("file " + fileName + " not found");
        } catch (IOException e) {
            System.out.println("failed to try to read the file: " + fileName);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid file syntax");
        }

        Board board = new Board(parser.getPieces(), parser.getSize());
        initialState = new GridLockState(board, parser.getPieces());
    }

    public static ConfigurationManager getInstance() {
        if (intance == null) {
            intance = new ConfigurationManager();
        }
        return intance;
    }

    public GridLockState getInitialState() {
        return this.initialState;
    }
}
