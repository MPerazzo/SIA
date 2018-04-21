package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.gridLock.GridLockState;
import ar.edu.itba.sia.gridLock.structures.Board;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigurationManager {

    private final static String fileName = "configFile-tp2.txt";
    private final Parser parser;

    private static ConfigurationManager intance = null;

    private ConfigurationManager() {
        this.parser = new Parser();
        try {
            parser.parse(fileName);
        } catch (FileNotFoundException e) {
            System.out.print("file " + fileName + " not found");
        }
        catch (IOException e) {
            System.out.println("failed to try to read the file: " + fileName);
        }
    }

    public static ConfigurationManager getIntance() {
        if (intance == null) {
            intance = new ConfigurationManager();
        }
        return intance;
    }

    public Parser getParser() {
        return parser;
    }

    public GridLockState initialState() {

        Board board = new Board(parser.getPieces(), parser.getSize());
        return new GridLockState(board, parser.getPieces());
    }
}
