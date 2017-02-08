package xiangstudenthsadoyan.versions.alphaxiangqi;

import xiangqi.common.*;

/**
 * Created by gnomeftlc on 2/7/17.
 */
public class AlphaXiangqiGame implements XiangqiGame {
    XiangqiPiece piece;
    public AlphaXiangqiGame(){
        piece = XiangqiPieceImp.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
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
        return piece;
    }
}
