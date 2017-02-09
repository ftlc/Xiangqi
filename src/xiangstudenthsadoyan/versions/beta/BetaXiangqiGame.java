package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.*;
import xiangstudenthsadoyan.versions.beta.XiangqiPieceImp;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by gnomeftlc on 2/7/17.
 */
public class BetaXiangqiGame implements XiangqiGame {
    Board board;
    String moveMessage;
    private int movecount = 0;
    private boolean valid = true;
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
            setMoveMessage("OUT OF BOUNDS");
            return MoveResult.ILLEGAL;
        }

            if(validateMove(source, destination)) {

                return MoveResult.OK;
            } else {
                return MoveResult.ILLEGAL;
            }
    }

    public boolean validateMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
        XiangqiCoordinateImp newSource = XiangqiCoordinateImp.copyConstructor(source);
        XiangqiCoordinateImp newDest = XiangqiCoordinateImp.copyConstructor(destination);

        if(getPieceAt(source, XiangqiColor.RED).getPieceType() == XiangqiPieceType.NONE){
            setMoveMessage("No Piece At Source");
            return false;
        }

        if(getPieceAt(source, XiangqiColor.RED).getColor() == XiangqiColor.BLACK){
            newSource = convertToBlack(newSource);
            newDest = convertToBlack(newDest);
        }

        XiangqiPieceImp piece = XiangqiPieceImp.copyConstructor(board.getPieceAt(newSource));
        final List<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>> validators =
                ValidateFactory.makeValidators(piece);

        for (BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> p : validators) {
            if(!p.test(newSource, newDest)) {
                String pieceName = piece.toString();
                setMoveMessage("Illegal " + pieceName + " Move");
                return false;
            }
        }

        return true;

    }


    public void setMoveMessage(String moveMessage) {
        setValid(false);
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

    public XiangqiCoordinateImp convertToBlack(XiangqiCoordinateImp original){
        int rank = board.getNumRanks() - original.getRank() + 1;
        int file = board.getNumFiles() - original.getFile() + 1;
        XiangqiCoordinate temp = XiangqiCoordinateImp.makeCoordinate(rank, file);
        XiangqiCoordinateImp toReturn = XiangqiCoordinateImp.copyConstructor(temp);
        return toReturn;
    }

}
