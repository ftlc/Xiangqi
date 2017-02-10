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
    private Board board;
    private String moveMessage;
    private XiangqiColor currentTurn;
    private int moveNumber = 1;
    private boolean valid = true;
    public BetaXiangqiGame(){
        board = Board.makeBoard(XiangqiGameVersion.BETA_XQ);
        currentTurn = XiangqiColor.RED;
    }

    private boolean isValid(){
        return valid;
    }
    private void setValid(boolean v){
        this.valid = v;
    }
    @Override
    public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {

        XiangqiCoordinateImp newSource = XiangqiCoordinateImp.copyConstructor(source);
        XiangqiCoordinateImp newDest = XiangqiCoordinateImp.copyConstructor(destination);

        if(!board.isInBounds(newSource) || !board.isInBounds(newDest)){
            setMoveMessage("OUT OF BOUNDS");
            return MoveResult.ILLEGAL;
        }


        if(validateMove(newSource, newDest)) {


            if(moveNumber >= 20){
                return MoveResult.DRAW;
            }
            board.movePiece(newSource, newDest);
            return MoveResult.OK;
        } else {
            return MoveResult.ILLEGAL;
        }
    }

    public boolean validateMove(XiangqiCoordinateImp source, XiangqiCoordinateImp destination) {

        if(getPieceAt(source, XiangqiColor.RED).getPieceType() == XiangqiPieceType.NONE){
            setMoveMessage("No Piece At Source");
            return false;
        }

        if(board.getPieceAt(source).getColor() != currentTurn){
            setMoveMessage("Piece Wrong Color");
            return false;
        }

        if(board.getPieceAt(destination).getColor() == board.getPieceAt(source).getColor()
                && !destination.equals(source)){
            setMoveMessage("Can't Capture Your Own Piece");
            return false;
        }

        if(!noPiecesInBetween(source, destination)) return false;

        if(getPieceAt(source, XiangqiColor.RED).getColor() == XiangqiColor.BLACK){
            source = convertToBlack(source);
            destination = convertToBlack(destination);
        }

        if(!runValidators(source, destination)) return false;


        moveNumber++;
        switchCurrentTurn();
        return true;

    }

    private boolean noPiecesInBetween(XiangqiCoordinateImp source, XiangqiCoordinateImp destination){
        if(destination.getFile() == source.getFile()){
            if(destination.getRank() > source.getRank()) {
                for (int i = 1; i < source.distanceTo(destination); i++) {
                    XiangqiCoordinateImp temp = XiangqiCoordinateImp.makeCoordinate(source.getRank() + i, source.getFile());
                    if (board.getPieceAt(temp).getColor() != XiangqiColor.NONE) {
                        setMoveMessage("Chariot Can't Jump Over A Piece");
                        return false;
                    }
                }
            }

            if(destination.getRank() < source.getRank()) {
                for (int i = 1; i < source.distanceTo(destination); i++) {
                    XiangqiCoordinateImp temp = XiangqiCoordinateImp.makeCoordinate(source.getRank() - i, source.getFile());
                    if (board.getPieceAt(temp).getColor() != XiangqiColor.NONE) {
                        setMoveMessage("Chariot Can't Jump Over A Piece");
                        return false;
                    }
                }
            }
        }

        if(destination.getRank() == source.getRank()){
            if(destination.getFile() > source.getFile()) {
                for (int i = 1; i < source.distanceTo(destination); i++) {
                    XiangqiCoordinateImp temp = XiangqiCoordinateImp.makeCoordinate(source.getRank(), source.getFile() + i);
                    if (board.getPieceAt(temp).getColor() != XiangqiColor.NONE) {
                        setMoveMessage("Chariot Can't Jump Over A Piece");
                        return false;
                    }
                }
            }

            if(destination.getFile() < source.getFile()) {
                for (int i = 1; i < source.distanceTo(destination); i++) {
                    XiangqiCoordinateImp temp = XiangqiCoordinateImp.makeCoordinate(source.getRank(), source.getFile() - i);
                    if (board.getPieceAt(temp).getColor() != XiangqiColor.NONE) {
                        setMoveMessage("Chariot Can't Jump Over A Piece");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean runValidators(XiangqiCoordinateImp source, XiangqiCoordinateImp destination){

        XiangqiPieceImp piece = XiangqiPieceImp.copyConstructor(board.getPieceAt(source));
        final List<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>> validators =
                ValidateFactory.makeValidators(piece);

        for (BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> p : validators) {
            if(!p.test(source, destination)) {
                setMoveMessage("Illegal " + piece.toString() + " Move");
                return false;
            }
        }

        return true;
    }
    private int switchCurrentTurn(){
        if(currentTurn == XiangqiColor.RED){
            currentTurn = XiangqiColor.BLACK;
        } else {
            currentTurn = XiangqiColor.RED;
        }
        return moveNumber;
    }


    private void setMoveMessage(String moveMessage) {
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
        XiangqiCoordinateImp temp = XiangqiCoordinateImp.copyConstructor(where);
        temp = convertToStandard(temp, aspect);
        return board.getPieceAt(temp);
    }

    private XiangqiCoordinateImp convertToStandard(XiangqiCoordinateImp where, XiangqiColor aspect) {
        if(aspect == XiangqiColor.BLACK){
            int rank = board.getNumRanks() - where.getRank() + 1;
            int file = board.getNumFiles() - where.getFile() + 1;
            XiangqiCoordinateImp standardCoord = XiangqiCoordinateImp.makeCoordinate(rank, file);
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
