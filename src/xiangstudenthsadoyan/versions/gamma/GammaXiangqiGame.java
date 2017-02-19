package xiangstudenthsadoyan.versions.gamma;

import xiangqi.common.*;
import xiangstudenthsadoyan.commonImplemenations.*;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by gnomeftlc on 2/18/17.
 */
public class GammaXiangqiGame implements XiangqiGame {

    private Board board;
    private String moveMessage;
    private XiangqiColor currentTurn;
    private int moveNumber = 1;
    private boolean valid = true;
    private State moveState;

    public GammaXiangqiGame(){
        board = Board.makeBoard(XiangqiGameVersion.GAMMA_XQ);
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
        if(board.getPieceAt(newSource).getColor() == getOppositeColor(currentTurn)){
            setMoveMessage("Piece Wrong Color");
            return MoveResult.ILLEGAL;
        }


        moveState = new State(board, newSource, newDest, currentTurn);
        if(runValidators(moveState)) {

            State ghostState = makeGhostMove(moveState);

            if(isGeneralUnderAttack(ghostState, currentTurn)){
                setMoveMessage("Can't Place General In Check");
                return MoveResult.ILLEGAL;
            }


            board.movePiece(newSource, newDest);
            moveNumber++;
            if(moveState.canGeneralsSeeEachOther()){
                if (currentTurn == XiangqiColor.BLACK) {
                    return MoveResult.RED_WINS;
                } else {
                    return MoveResult.BLACK_WINS;
                }
            }
            if(checkmate(moveState)) {
                if (currentTurn == XiangqiColor.RED) {
                    return MoveResult.RED_WINS;
                } else {
                    return MoveResult.BLACK_WINS;
                }
            }
            if(moveNumber > 50){
                return MoveResult.DRAW;
            }
            switchCurrentTurn();
            return MoveResult.OK;
        } else {
            return MoveResult.ILLEGAL;
        }
    }

    public State makeGhostMove(State state){
        State ghostState = State.copyConstructor(state);
        ghostState.movePiece(state.getSource(), state.getDestination());

        return ghostState;
    }

    public boolean checkmate(State state){
        XiangqiCoordinateImp kinglocation = state.getKingsLocation(getOppositeColor(currentTurn));


        if(isLocationUnderAttack(state, kinglocation)){
            return !anyValidMoves(state, kinglocation);
        } else {
            return false;
        }

    }

    public boolean anyValidMoves(State state, XiangqiCoordinateImp kinglocation){
        State tempState = State.copyConstructor(state);
        XiangqiColor color = tempState.getBoard().getPieceAt(kinglocation).getColor();


        HashSet<XiangqiCoordinateImp> coords = tempState.getTheLocationsOfAllPiecesOfColor(color);
        for(XiangqiCoordinateImp c : coords) {
            tempState.setSource(c);
            tempState.setAspect(color);
            int numRanks = state.getBoard().getNumRanks();
            int numFiles = state.getBoard().getNumFiles();
            for (int i = 1; i <= numRanks; i++) {
                for (int j = 1; j <= numFiles; j++) {
                    XiangqiCoordinateImp DTemp  = XiangqiCoordinateImp.makeCoordinate(j, i);
                    tempState.setDestination(DTemp);
                    if (runValidators(tempState)) {
                        State ghostState = makeGhostMove(tempState);
                        if (!isGeneralUnderAttack(ghostState, color)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    public boolean runValidators(State state) {

        XiangqiPieceImp piece = XiangqiPieceImp.copyConstructor(board.getPieceAt(state.getSource()));

        final List<Predicate<State>> stateValidators = ValidateFactory.makeStateValidators(piece);
        for(Predicate<State> p: stateValidators){
            if(!p.test(state)){
                setMoveMessage(state.getMoveMessage());
                return false;
            }
        }

        return true;
    }

    public boolean isGeneralUnderAttack(State ghostState, XiangqiColor kingColor){
        XiangqiCoordinateImp kinglocation = ghostState.getKingsLocation(kingColor);
        return isLocationUnderAttack(ghostState, kinglocation);
    }

    private boolean isLocationUnderAttack(State theState, XiangqiCoordinateImp location){
        State tempState = State.copyConstructor(theState);
        tempState.setDestination(location);
        if(tempState.getAspect() != XiangqiColor.NONE){
            HashSet<XiangqiCoordinateImp> coords = tempState.getTheLocationsOfAllPiecesOfColor(getOppositeColor(tempState.getBoard().getPieceAt(location).getColor()));
            for(XiangqiCoordinateImp c : coords){
                tempState.setSource(c);
                if(runValidators(tempState)){
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
