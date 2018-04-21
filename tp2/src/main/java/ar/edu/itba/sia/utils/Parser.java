package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.gridLock.GridLockPiece;
import ar.edu.itba.sia.gridLock.GridLockPieceType;
import ar.edu.itba.sia.gridLock.structures.Position2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private final static String BOARD = "board", SIZE = "size";

    private int size;
    private List<GridLockPiece> pieces;

    public Parser() {
        this.pieces = new LinkedList<>();
    }

    public void parse(String fileName) throws IOException {

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while((line = bufferedReader.readLine())!=null) {

            String [] args = line.split(":");

            if(args[0].equals(Parser.BOARD)) {
                String [] piecesArgs = args[1].split(";");

                int lenght = piecesArgs.length;
                for(int i = 0 ; i < lenght ; i++) {

                    String [] pieceArgs = piecesArgs[i].split(",");
                    Position2D point = new Position2D(Integer.parseInt(pieceArgs[0]),Integer.parseInt(pieceArgs[1]));
                    int size = Integer.parseInt(pieceArgs[2]);

                    if(pieceArgs[3].equals("h")) {

                        pieces.add(new GridLockPiece(point, size, GridLockPieceType.HORIZONTAL));

                    } else if(pieceArgs[3].equals("v")) {

                        pieces.add(new GridLockPiece(point, size, GridLockPieceType.VERTICAL));

                    }
                }

            } if(args[0].equals(Parser.SIZE)) {

               this.size = Integer.parseInt(args[1]);

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
