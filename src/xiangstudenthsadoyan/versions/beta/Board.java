package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.*;

import java.util.HashMap;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class Board {
    private HashMap<XiangqiCoordinateImp, XiangqiPiece> board;
    private int numRanks;
    private int numFiles;
    public static Board makeBoard(XiangqiGameVersion version) {
        return new Board(version);
    }
    private Board(XiangqiGameVersion version) {
        switch(version) {
            case BETA_XQ:
                numRanks = numFiles = 5;
                board = new HashMap<XiangqiCoordinateImp, XiangqiPiece>();

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

        }
    }

    public void movePiece(XiangqiCoordinateImp from, XiangqiCoordinateImp to){
        XiangqiPiece temp = board.get(from);
        board.put(to, temp);
        board.remove(from);
    }

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

    public XiangqiPiece getPieceAt(XiangqiCoordinateImp where){
        for(XiangqiCoordinateImp c: board.keySet()) {
            if(c.equals(where)) {
                return board.get(c);
            }
        }
        return XiangqiPieceImp.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
}
