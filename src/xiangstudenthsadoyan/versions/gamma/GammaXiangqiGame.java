package xiangstudenthsadoyan.versions.gamma;

import xiangqi.common.*;
import xiangstudenthsadoyan.commonImplemenations.*;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.function.Predicate;

/**
 * Created by gnomeftlc on 2/18/17.
 */
public class GammaXiangqiGame implements XiangqiGame {

    //Variables
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

        // Copy Constructors
        XiangqiCoordinateImp newSource = XiangqiCoordinateImp.copyConstructor(source);
        XiangqiCoordinateImp newDest = XiangqiCoordinateImp.copyConstructor(destination);


        // Standardize Coordinates
        if(currentTurn == XiangqiColor.BLACK){
            newSource = convertCoordinate(newSource);
            newDest = convertCoordinate(newDest);
        }

        //Check Piece correct color
        if(board.getPieceAt(newSource).getColor() == getOppositeColor(currentTurn)){
            setMoveMessage("Piece Wrong Color");
            return MoveResult.ILLEGAL;
        }


        //Create Official State
        moveState = new State(board, newSource, newDest, currentTurn);

        if(runAndTestGeneral(moveState)){


            //Update the board
            moveState.movePiece(newSource, newDest);
            moveNumber++;


            //Check for checkmate
            if(checkmate(moveState) || stalemate(moveState)) {
                if (currentTurn == XiangqiColor.RED) {
                    return MoveResult.RED_WINS;
                } else {
                    return MoveResult.BLACK_WINS;
                }
            }

            //Check for draw
            if(moveNumber > 50){
                return MoveResult.DRAW;
            }


            //Update current turn
            switchCurrentTurn();
            return MoveResult.OK;
        } else {
            return MoveResult.ILLEGAL;
        }
    }

    /**
     * Returns true if the opposite side has no valid moves
     * @param theState to check
     * @return boolean
     */
    public boolean stalemate(State theState){
        return !anyValidMoves(theState);
    }

    /**
     * Makes ghost move
     * @param state the state to copy
     * @return ghostState with the ghost move
     */
    public State makeGhostMove(State state){
        State ghostState = State.copyConstructor(state);
        ghostState.movePiece(state.getSource(), state.getDestination());

        return ghostState;
    }

    /**
     * Return if it's a checkmate state
     * @param state to check
     * @return boolean indicating checkmate or not
     */
    public boolean checkmate(State state){
        if(isGeneralUnderAttack(state, getOppositeColor(state.getAspect()))){
            return !anyValidMoves(state);
        } else {
            return false;
        }

    }

    /**
     * Run validators and make a ghost move to test the general is not exposed to check
     * @param state to test on
     * @return boolean
     */
    public boolean runAndTestGeneral(State state){
        if(runValidators(state)){

            //Make Ghost State to validate General Not Under attack
            State ghostState = makeGhostMove(state);

            //Validate general not under attack
            if(isGeneralUnderAttack(ghostState, ghostState.getAspect())){
                setMoveMessage("Can't Place General In Check");
                return false;
            }

            return true;
        }

        return false;

    }
    /**
     * Takes a state and returns whether there are any legal moves
     * @param state
     * @return boolean indicating valid moves or not
     */
    public boolean anyValidMoves(State state){

        //The king opposite of the state's turn
        XiangqiCoordinateImp kinglocation = state.getKingsLocation(getOppositeColor(state.getAspect()));
        State tempState = State.copyConstructor(state);
        //Color of the king
        XiangqiColor color = tempState.getBoard().getPieceAt(kinglocation).getColor();


        //The Coordinates of all the pieces of this color
        HashSet<XiangqiCoordinateImp> coords = tempState.getTheLocationsOfAllPiecesOfColor(color);
        for(XiangqiCoordinateImp c : coords) {
            tempState.setSource(c);
            tempState.setAspect(color);

            //All the valid locations on the board.
            HashSet<XiangqiCoordinateImp> locations = state.getBoard().getAllBoardLocations();


            for(XiangqiCoordinateImp l: locations){
                tempState.setDestination(l);
                //See if piece can make that move
                if(runAndTestGeneral(tempState)){
                    return true;
                }

            }
        }

        return false;
    }


    /**
     * Runs all of the Predicate validators to determine if move from source to dest is legal
     * @param state to test
     * @return boolean
     */
    public boolean runValidators(State state) {

        XiangqiPieceImp piece = XiangqiPieceImp.copyConstructor(board.getPieceAt(state.getSource()));

        final List<Predicate<State>> stateValidators = ValidateFactory.makeGammaValidators(piece, state);
        for(Predicate<State> p: stateValidators){
            if(!p.test(state)){
                setMoveMessage(state.getMoveMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether general of kingColor is under attack in given state
     * @param ghostState to check
     * @param kingColor the color of the general
     * @return boolean
     */
    public boolean isGeneralUnderAttack(State ghostState, XiangqiColor kingColor){
        XiangqiCoordinateImp kinglocation = ghostState.getKingsLocation(kingColor);
        return isLocationUnderAttack(ghostState, kinglocation) || ghostState.canGeneralsSeeEachOther();
    }

    /**
     * Returns whether location is under attack in a given state
     * @param theState the state to check
     * @param location the location to check
     * @return boolean
     */
    private boolean isLocationUnderAttack(State theState, XiangqiCoordinateImp location){
        //Deep Copy not to modify original state
        State tempState = State.copyConstructor(theState);
        tempState.setDestination(location);

        //Get locations of all attacking pieces
        HashSet<XiangqiCoordinateImp> coords = tempState.getTheLocationsOfAllPiecesOfColor(getOppositeColor(tempState.getBoard().getPieceAt(location).getColor()));
        tempState.setAspect(getOppositeColor(tempState.getAspect()));
        for(XiangqiCoordinateImp c : coords){
            //Set stateSource as the piece location
            tempState.setSource(c);
            if(runValidators(tempState)){
                return true;
            }
        }
        return false;
    }

    /**
     * Takes a color and returns the opposite color
     * @param color to take
     * @return opposite color
     */
    private XiangqiColor getOppositeColor(XiangqiColor color){
        if(color == XiangqiColor.RED) {
            return XiangqiColor.BLACK;
        } else {
            return XiangqiColor.RED;

        }
    }


    private void switchCurrentTurn(){
        currentTurn = getOppositeColor(currentTurn);
    }


    /**
     * Sets move message. Also sets valid to be false
     * @param moveMessage new message to set
     */
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

        if(!board.isInBounds(temp)){
            Throwable ex = new Throwable();
            throw new CompletionException(ex);
        }
        if(aspect == XiangqiColor.BLACK) {
            temp = convertCoordinate(temp);
        }
        return board.getPieceAt(temp);
    }


    /**
     * Standard converted from Red to Black or vice versa
     * @param original coord
     * @return converted coord
     */
    public XiangqiCoordinateImp convertCoordinate(XiangqiCoordinateImp original){
        int rank = board.getNumRanks() - original.getRank() + 1;
        int file = board.getNumFiles() - original.getFile() + 1;
        XiangqiCoordinateImp standardCoord = XiangqiCoordinateImp.makeCoordinate(rank, file);
        return standardCoord;
    }


}
