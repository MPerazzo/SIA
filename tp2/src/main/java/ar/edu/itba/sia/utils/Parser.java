package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockPieceType;
import ar.edu.itba.sia.gridLock.structures.Position2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Parser {

    private final static String BOARD_HEADER = "board", SIZE_HEADER = "size";
    private final static Integer PIECE_X = 0, PIECE_Y = 1, PIECE_SIZE = 2, PIECE_ORIENTATION = 3,
            HEADER_POSITION = 0, HEADER_VALUE = 1;
    private final static String VALUE_SEPARATOR = ":", PIECE_SEPARATOR = ";", PIECEARG_SEPARATOR = ",";
    private final static String HORIZONTAL_ORIENTATION = "h", VERTICAL_ORIENTATION = "v";



    private int size;
    private List<GridLockPiece> pieces;

    public Parser() {
        this.pieces = new LinkedList<>();
    }

    public void parse(String fileName) throws IOException {

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while((line = bufferedReader.readLine()) != null) {

            String [] args = line.split(VALUE_SEPARATOR);

            if(args[HEADER_POSITION].equals(BOARD_HEADER)) {
                String [] piecesArgs = args[HEADER_VALUE].split(PIECE_SEPARATOR);

                int lenght = piecesArgs.length;
                for(int i = 0 ; i < lenght ; i++) {

                    String [] pieceArgs = piecesArgs[i].split(PIECEARG_SEPARATOR);
                    Position2D point = new Position2D(Integer.parseInt(pieceArgs[PIECE_X]),
                            Integer.parseInt(pieceArgs[PIECE_Y]));
                    int size = Integer.parseInt(pieceArgs[PIECE_SIZE]);

                    if(pieceArgs[PIECE_ORIENTATION].equals(HORIZONTAL_ORIENTATION)) {

                        pieces.add(new GridLockPiece(point, size, GridLockPieceType.HORIZONTAL));

                    } else if(pieceArgs[PIECE_ORIENTATION].equals(VERTICAL_ORIENTATION)) {

                        pieces.add(new GridLockPiece(point, size, GridLockPieceType.VERTICAL));

                    }
                }

            } if(args[HEADER_POSITION].equals(SIZE_HEADER)) {

               this.size = Integer.parseInt(args[HEADER_VALUE]);

            }
        }
        bufferedReader.close();

    }

    public int getSize() {
        return size;
    }

    public List<GridLockPiece> getPieces() {
        return pieces;
    }
}
