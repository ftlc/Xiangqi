package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.*;
import xiangstudenthsadoyan.versions.alphaxiangqi.XiangqiPieceImp;

import java.util.HashMap;

/**
 * Created by gnomeftlc on 2/7/17.
 */
public class BetaXiangqiGame implements XiangqiGame {
    Board board;
    public BetaXiangqiGame(){
        board = Board.makeBoard(XiangqiGameVersion.BETA_XQ);
    }
    private int movecount = 0;
    private boolean valid;

    private boolean isValid(){
        return valid;
    }
    private void setValid(boolean v){
        this.valid = v;
    }
    @Override
    public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
        if(destination.getRank() != 1 || destination.getFile() != 2 || source.getFile() != 1 || source.getRank() != 1){
            setValid(false);
            return MoveResult.ILLEGAL;
        }
        if(movecount == 0) {
            movecount++;
            return MoveResult.OK;
        } else {
            return MoveResult.RED_WINS;
        }
    }

    @Override
    public String getMoveMessage() {
        return isValid() ? null : "Invalid Move";
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
