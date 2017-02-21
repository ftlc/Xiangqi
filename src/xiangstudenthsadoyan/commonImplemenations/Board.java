package xiangstudenthsadoyan.commonImplemenations;

import xiangqi.common.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class Board {
    private HashMap<XiangqiCoordinateImp, XiangqiPieceImp> board;
    private int numRanks;
    private int numFiles;
    private XiangqiGameVersion version;
    private HashSet<XiangqiCoordinateImp> locations = null;


    /**
     * Takes an existing board and makes a deep copy
     * @param b board to copy
     * @return new board
     */
    public static Board copyConstructor(Board b){
        Board newBoard = new Board(b.version);
        newBoard.board = new HashMap<XiangqiCoordinateImp, XiangqiPieceImp>(b.board);
        newBoard.numRanks = b.getNumRanks();
        newBoard.numFiles = b.getNumFiles();
        return newBoard;
    }

    /**
     * Static creation method.
     * @param version of the game to be created
     * @return board
     */
    public static Board makeBoard(XiangqiGameVersion version) {
        return new Board(version);
    }
    private Board(XiangqiGameVersion version) {
        this.version = version;
        switch(version) {
            case BETA_XQ:
                numRanks = numFiles = 5;
                board = new HashMap<XiangqiCoordinateImp, XiangqiPieceImp>();

                //Initialize Red pieces
                board.put(XiangqiCoordinateImp.makeCoordinate(1,3), XiangqiPieceImp.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,1), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,2), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,4), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,5), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(2,3), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));

                //Initialize Black pieces
                board.put(XiangqiCoordinateImp.makeCoordinate(5,3), XiangqiPieceImp.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(5,1), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(5,2), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(5,4), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(5,5), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(4,3), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
                break;

            case GAMMA_XQ:
                numRanks = 10;
                numFiles = 9;
                board = new HashMap<>();


                //Initialize Red pieces
                board.put(XiangqiCoordinateImp.makeCoordinate(1,5), XiangqiPieceImp.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,1), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,3), XiangqiPieceImp.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,4), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,6), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,7), XiangqiPieceImp.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(1,9), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(4,1), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(4,3), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(4,5), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(4,7), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
                board.put(XiangqiCoordinateImp.makeCoordinate(4,9), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));

                //Initialize Black pieces
                board.put(XiangqiCoordinateImp.makeCoordinate(10,5), XiangqiPieceImp.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(10,1), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(10,3), XiangqiPieceImp.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(10,4), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(10,6), XiangqiPieceImp.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(10,7), XiangqiPieceImp.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(10,9), XiangqiPieceImp.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(7,1), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(7,3), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(7,5), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(7,7), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
                board.put(XiangqiCoordinateImp.makeCoordinate(7,9), XiangqiPieceImp.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
                break;
        }
    }

    /**
     * Move a piece from one location on the board to another
     * @param from
     * @param to
     */
    public void movePiece(XiangqiCoordinateImp from, XiangqiCoordinateImp to){
        XiangqiPieceImp temp = board.get(from);
        board.put(to, temp);
        board.remove(from);
    }

    /**
     * Is coordinate within bounds of the board. Depends on game version.
     * @param c to check
     * @return boolean
     */
    public boolean isInBounds(XiangqiCoordinateImp c) {
        if(c.getFile() <= 0 || c.getRank() <= 0) {
            return false;
        }
        return (c.getFile() <= getNumFiles() && c.getRank() <= getNumRanks());
    }
    public int getNumFiles() {
        return numFiles;
    }

    public int getNumRanks() {
        return numRanks;
    }

    /**
     * Cycle through board and return the piece at the given location. If no piece there,
     * return a None piece.
     * @param where location
     * @return piece
     */
    public XiangqiPieceImp getPieceAt(XiangqiCoordinateImp where){
        for(XiangqiCoordinateImp c: board.keySet()) {
            if(c.equals(where)) {
                return board.get(c);
            }
        }
        return XiangqiPieceImp.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
    }


    /**
     * Get all the possible locations on this board
     * @return HashSet of all the locations
     */
    public HashSet<XiangqiCoordinateImp> getAllBoardLocations(){
        if(this.locations == null) {
            locations = new HashSet<>();
            for (int i = 1; i <= numRanks; i++) {
                for (int j = 1; j <= numFiles; j++) {
                    locations.add(XiangqiCoordinateImp.makeCoordinate(i, j));
                }
            }
        }
        return locations;
    }

    /**
     * return the location of the king of a given color
     * @param color the color of the king
     * @return the location of the king
     */
    public XiangqiCoordinateImp getKingsLocation(XiangqiColor color){
        XiangqiPieceImp king = XiangqiPieceImp.makePiece(XiangqiPieceType.GENERAL, color);
        for(XiangqiCoordinateImp c: board.keySet()) {
            if(board.get(c).equals(king)){
                return c;
            }
        }

        //Should never be reached. Just here so java doesn't complain.
        return null;
    }


    /**
     * Takes an aspect and a location and returns whether that location is across the river
     * @param c coordinate
     * @param aspect perspective
     * @return boolean
     */
    public boolean isAcrossTheRiver(XiangqiCoordinateImp c, XiangqiColor aspect){

        if(aspect == XiangqiColor.RED){
            if(c.getRank() > 5){
                return true;
            }
        }
        if(aspect == XiangqiColor.BLACK){
            if(c.getRank() <=5){
                return true;
            }
        }
        return false;
    }


    /**
     * Takes two locations and returns whether this move crosses the river
     * @param source source location
     * @param dest destination location
     * @param aspect perspective
     * @return
     */
    public boolean notCrossingTheRiver(XiangqiCoordinateImp source, XiangqiCoordinateImp dest, XiangqiColor aspect){
        if(source.getRank() > 5 && dest.getRank() <= 5){
            return false;
        }
        if(source.getRank() <=5 && dest.getRank() > 5){
            return false;
        }
        return true;
    }

    /**
     * Returns whether the given coordinate is in a palace
     * @param c coordinate to test
     * @return boolean
     */
    public boolean inPalace(XiangqiCoordinateImp c){
        if(version == XiangqiGameVersion.BETA_XQ) {
            return (c.getRank() == 1 || c.getRank() == 5) && c.getFile() >= 2 && c.getFile() <= 4;
        }
        if(version == XiangqiGameVersion.GAMMA_XQ) {
            return (c.getRank() >=8 || c.getRank() <= 3) && c.getFile() >= 4 && c.getFile() <= 6;
        }

        return false;
    }

    public Set getAllOccupiedLocations(){
        return board.keySet();
    }

    /**
     * Get the locations of all pieces of a certain color
     * @param color to test
     * @return Set of locations
     */
    public HashSet getTheLocationsOfAllPiecesOfColor(XiangqiColor color){
        HashSet<XiangqiCoordinateImp> coords = new HashSet<>();
        for(XiangqiCoordinateImp c: board.keySet()){
            if(board.get(c).getColor() == color){
                coords.add(c);
            }
        }
        return coords;
    }
}
