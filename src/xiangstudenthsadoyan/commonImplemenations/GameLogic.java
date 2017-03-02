package xiangstudenthsadoyan.commonImplemenations;


import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGameVersion;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by gnomeftlc on 3/2/17.
 */
public class GameLogic {

    private boolean valid = true;
    private String moveMessage;
    private XiangqiGameVersion version;
    public GameLogic(XiangqiGameVersion version){
        this.version = version;
    }

    /**
     * Sets move message. Also sets valid to be false
     * @param moveMessage new message to set
     */
    public void setMoveMessage(String moveMessage) {
        setValid(false);
        this.moveMessage = moveMessage;
    }


    private boolean isValid(){
        return valid;
    }
    private void setValid(boolean v){
        this.valid = v;
    }
    public String getMoveMessage() {
        if(isValid()) return null;

        String temp = moveMessage;
        moveMessage = "";
        return temp;
    }
    /**
     * Converts the color to a move win
     * @param color to convert
     * @return the relevant move result
     */
    public MoveResult convertColorToMoveWin(XiangqiColor color){
        if (color == XiangqiColor.RED) {
            return MoveResult.RED_WINS;
        } else {
            return MoveResult.BLACK_WINS;
        }
    }


    /**
     * Runs all of the Predicate validators to determine if move from source to dest is legal
     * @param state to test
     * @return boolean
     */
    public boolean runValidators(State state) {

        XiangqiPieceImp piece = XiangqiPieceImp.copyConstructor(state.getBoard().getPieceAt(state.getSource()));

        final List<Predicate<State>> stateValidators = ValidateFactory.makeValidators(piece, state, version);
        for(Predicate<State> p: stateValidators){
            if(!p.test(state)){
                setMoveMessage(state.getMoveMessage());
                return false;
            }
        }
        return true;
    }


    /**
     * Returns whether location is under attack in a given state
     * @param theState the state to check
     * @param location the location to check
     * @return boolean
     */
    public boolean isLocationUnderAttack(State theState, XiangqiCoordinateImp location){
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
    public XiangqiColor getOppositeColor(XiangqiColor color){
        if(color == XiangqiColor.RED) {
            return XiangqiColor.BLACK;
        } else {
            return XiangqiColor.RED;
        }
    }

    /**
     * Standard converted from Red to Black or vice versa
     * @param original coord
     * @return converted coord
     */
    public XiangqiCoordinateImp convertCoordinate(XiangqiCoordinateImp original, Board board){
        int rank = board.getNumRanks() - original.getRank() + 1;
        int file = board.getNumFiles() - original.getFile() + 1;
        XiangqiCoordinateImp standardCoord = XiangqiCoordinateImp.makeCoordinate(rank, file);
        return standardCoord;
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
}
