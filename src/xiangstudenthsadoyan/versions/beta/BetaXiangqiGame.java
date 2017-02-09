package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.*;
import xiangstudenthsadoyan.versions.alphaxiangqi.XiangqiPieceImp;

import java.util.HashMap;

/**
 * Created by gnomeftlc on 2/7/17.
 */
public class BetaXiangqiGame implements XiangqiGame {
    Board board;
    String moveMessage;
    private int movecount = 0;
    private boolean valid;
    public BetaXiangqiGame(){
        board = Board.makeBoard(XiangqiGameVersion.BETA_XQ);
    }

    private boolean isValid(){
        return valid;
    }
    private void setValid(boolean v){
        this.valid = v;
    }
    @Override
    public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
        if(!board.isInBounds(source) || !board.isInBounds(destination)){
            setValid(false);
            setMoveMessage("OUT OF BOUNDS");
            return MoveResult.ILLEGAL;
        }

        return MoveResult.OK;
    }

    public void setMoveMessage(String moveMessage) {
        this.moveMessage = moveMessage;
    }

    @Override
    public String getMoveMessage() {
        if(isValid()) return null;

        String temp = moveMessage;
        moveMessage = "";
        return temp;
    }

    @Override
    public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
        XiangqiCoordinate std = convertToStandard(where, aspect);
        return board.getPieceAt(std);
    }

    private XiangqiCoordinate convertToStandard(XiangqiCoordinate where, XiangqiColor aspect) {
        if(aspect == XiangqiColor.BLACK){
            int rank = board.getNumRanks() - where.getRank() + 1;
            int file = board.getNumFiles() - where.getFile() + 1;
            XiangqiCoordinate standardCoord = XiangqiCoordinateImp.makeCoordinate(rank, file);
            return standardCoord;
        }

        return where;
    }


}
