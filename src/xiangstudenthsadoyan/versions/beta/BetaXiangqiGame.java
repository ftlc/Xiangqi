package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.*;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by gnomeftlc on 2/7/17.
 */
public class BetaXiangqiGame implements XiangqiGame {
    private Board board;
    private String moveMessage;
    private XiangqiColor currentTurn;
    private int moveNumber = 1;
    private boolean valid = true;
    private State moveState;
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


        if(currentTurn == XiangqiColor.BLACK){
            newSource = convertCoordinate(newSource);
            newDest = convertCoordinate(newDest);
        }
        if(board.getPieceAt(newSource).getColor() != currentTurn){
            setMoveMessage("Piece Wrong Color");
            return MoveResult.ILLEGAL;
        }


        if(validateMove(newSource, newDest)) {

            board.movePiece(newSource, newDest);
            moveNumber++;
            if(moveNumber > 20){
                return MoveResult.DRAW;
            }
            if(checkmate()) {
                if (currentTurn == XiangqiColor.RED) {
                    return MoveResult.RED_WINS;
                } else {
                    return MoveResult.BLACK_WINS;
                }
            }
            switchCurrentTurn();
            return MoveResult.OK;
        } else {
            return MoveResult.ILLEGAL;
        }
    }

    public boolean checkmate(){
        XiangqiCoordinateImp kinglocation = board.getKingsLocation(getOppositeColor(currentTurn));
        if(isLocationUnderAttack(kinglocation)){
            return(!canGeneralMove(kinglocation));
        } else {
            return false;
        }
    }


    public boolean canGeneralMove(XiangqiCoordinateImp kinglocation){
        XiangqiColor color = board.getPieceAt(kinglocation).getColor();
        for(int i = 2; i <= 4; i++) {
            if(color == XiangqiColor.BLACK) {
                if(validateMove(kinglocation, XiangqiCoordinateImp.makeCoordinate(5, i))){
                    return true;
                }
            } else {
                if(validateMove(kinglocation, XiangqiCoordinateImp.makeCoordinate(1, i))){
                    return true;
                }
            }
        }



        return false;
    }

    public boolean validateMove(XiangqiCoordinateImp source, XiangqiCoordinateImp destination) {

        moveState = new State(board, source, destination, currentTurn);


        XiangqiPieceImp piece = XiangqiPieceImp.copyConstructor(board.getPieceAt(source));

        final List<Predicate<State>> stateValidators = ValidateFactory.makeStateValidators(piece);
        for(Predicate<State> p: stateValidators){
            if(!p.test(moveState)){
                setMoveMessage(moveState.getMoveMessage());
                return false;
            }
        }
        return true;



    }

    private boolean isLocationUnderAttack(XiangqiCoordinateImp location){
        XiangqiColor color = board.getPieceAt(location).getColor();

        if(color!= XiangqiColor.NONE){
            HashSet<XiangqiCoordinateImp> coords = board.getTheLocationsOfAllPiecesOfColor(getOppositeColor(color));
            for(XiangqiCoordinateImp c : coords){
                if(validateMove(c, location)){
                    return true;
                }
            }

        }
        return false;
    }

    private XiangqiColor getOppositeColor(XiangqiColor color){
        if(color == XiangqiColor.RED) {
            return XiangqiColor.BLACK;
        } else if(color == XiangqiColor.BLACK){
            return XiangqiColor.RED;
        } else {
            return XiangqiColor.NONE;
        }
    }

    private void switchCurrentTurn(){
        if(currentTurn == XiangqiColor.RED){
            currentTurn = XiangqiColor.BLACK;
        } else {
            currentTurn = XiangqiColor.RED;
        }
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

        if(aspect == XiangqiColor.BLACK) {
            temp = convertCoordinate(temp);
        }
        return board.getPieceAt(temp);
    }




    public XiangqiCoordinateImp convertCoordinate(XiangqiCoordinateImp original){
        int rank = board.getNumRanks() - original.getRank() + 1;
        int file = board.getNumFiles() - original.getFile() + 1;
        XiangqiCoordinateImp standardCoord = XiangqiCoordinateImp.makeCoordinate(rank, file);
        return standardCoord;
    }


}
