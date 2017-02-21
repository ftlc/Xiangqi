package xiangstudenthsadoyan.commonImplemenations;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPieceType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gnomeftlc on 2/16/17.
 */
public class State {
    private Board board;
    private XiangqiCoordinateImp source;
    private XiangqiCoordinateImp destination;
    private String moveMessage;
    private XiangqiColor aspect;


    /**
     * Copy constructor. Deep copies old state into new state
     * @param oldState to copy
     * @return new state
     */
    public static State copyConstructor(State oldState){
        State newState = new State(
                Board.copyConstructor(oldState.board),
                oldState.source,
                oldState.destination,
                oldState.aspect);
        return newState;
    }



    public State(Board board, XiangqiCoordinateImp source, XiangqiCoordinateImp dest, XiangqiColor aspect){
       this.board = board;
       this.source = source;
       this.destination = dest;
       this.aspect = aspect;
    }

    public void setSource(XiangqiCoordinateImp source) {
        this.source = source;
    }

    public void setDestination(XiangqiCoordinateImp destination) {
        this.destination = destination;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Moves the piece on the board.
     * @param src from
     * @param dest to
     */
    public void movePiece(XiangqiCoordinateImp src, XiangqiCoordinateImp dest){
        board.movePiece(src, dest);
        source = dest;
    }
    public XiangqiColor getAspect() {
        return aspect;
    }

    /**
     * returns the location of the king of the given color
     * @param color of the king
     * @return XiangqiCoordinateImp location
     */
    public XiangqiCoordinateImp getKingsLocation(XiangqiColor color){
        return board.getKingsLocation(color);
    }

    public XiangqiCoordinateImp getDestination() {
        return destination;
    }

    public XiangqiCoordinateImp getSource() {
        return source;
    }

    public void setMoveMessage(String moveMessage) {
        this.moveMessage = moveMessage;
    }

    public String getMoveMessage() {
        return moveMessage;
    }

    /**
     * Return if there is a piece at the source point
     * @return boolean
     */
    public boolean pieceAtSource(){
        if(board.getPieceAt(source).getPieceType() == XiangqiPieceType.NONE){
            setMoveMessage("No Piece At Source");
            return false;
        }
        return true;
    }

    /**
     * Returns a HashSet of all the pieces of a given color
     * @param color of the pieces
     * @return HashSet of XiangqiCoordinateImp
     */
    public HashSet<XiangqiCoordinateImp> getTheLocationsOfAllPiecesOfColor(XiangqiColor color){
        return board.getTheLocationsOfAllPiecesOfColor(color);
    }

    /**
     * Return whether the move is not on own piece
     * @return boolean
     */
    public boolean moveOnOwnPiece(){
        if(board.getPieceAt(destination).getColor() == board.getPieceAt(source).getColor()
                && !destination.equals(source)){
            setMoveMessage("Can't Capture Your Own Piece");
            return false;
        }
        return true;
    }

    /**
     * Return whether source and destination are both in bounds
     * @return
     */
    public boolean isInBounds(){
        if(!board.isInBounds(source) || !board.isInBounds(destination)){
            setMoveMessage("OUT OF BOUNDS");
            return false;
        }
        return true;
    }

    /**
     * Return whether the source and destination are different
     * @return boolean
     */
    public boolean isDifferent(){
        if(source.equals(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    /**
     * Return whether the destination is within the palace
     * @return boolean
     */
    public boolean inPalace(){
        if(!board.inPalace(destination)){
            setMoveMessage(board.getPieceAt(source) + " Can't Leave Palace");
            return false;
        }
        return true;
    }

    /**
     * Return whether the destination is Diagonally Adjacent to the source
     * @return boolean
     */
    public boolean isDiagonallyAdjascent(){
        if(!source.isDiagonallyAdjacent(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }


    /**
     * Return whether destination is diagonal to the source
     * @return
     */
    public boolean isDiagonal(){
        if(!source.isDiagonal(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    /**
     * Return whether destination is adjacent to the source
     * @return boolean
     */
    public boolean isAdjascent(){
        if(!(source.distanceTo(destination) == 1)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    public void setAspect(XiangqiColor aspect) {
        this.aspect = aspect;
    }


    /**
     * Validate that the move from source to destination doesn't cross the river
     * @return boolean
     */
    public boolean notCrossingTheRiver(){
        if(!board.notCrossingTheRiver(source, destination, aspect)){
            setMoveMessage(board.getPieceAt(source) + " Can't Cross the River");
            return false;
        }
        return true;
    }

    /**
     * Validate destination is not backward from the source
     * @return
     */
    public boolean isNotBackward(){
        if(!(source.isNotBackWard(destination, aspect))){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }


    /**
     * Validate the destination is forward than the source
     * @return boolean
     */
    public boolean isForward(){
        if(!(source.isForward(destination, aspect))){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    /**
     * Validate destination is orthogonal to source
     * @return boolean
     */
    public boolean isOrthogonal(){
        if(!source.isOrthogonal(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;

    }

    /**
     * Return whether generals can see each other
     * @return boolean
     */
    public boolean canGeneralsSeeEachOther(){
        XiangqiCoordinateImp redKing = board.getKingsLocation(XiangqiColor.RED);
        XiangqiCoordinateImp blackKing = board.getKingsLocation(XiangqiColor.BLACK);

        if(redKing.getFile() != blackKing.getFile()){
            return false;
        }
        if(noPiecesInBetween(redKing, blackKing)){
            return true;
        } else {
            return false;
        }

    }


    /**
     * Validate no skills in between given source and dest
     * @param source
     * @param dest
     * @return boolean
     */
    public boolean noPiecesInBetween(XiangqiCoordinateImp source, XiangqiCoordinateImp dest){

        Set<XiangqiCoordinateImp> occupied = board.getAllOccupiedLocations();
        for(XiangqiCoordinateImp o: occupied){
            if(o.equals(source) || o.equals(dest)){
                continue;
            }
            if(source.isLocationBetween(o,dest)){
                setMoveMessage("Can't Jump Over A Piece");
                return false;
            }
        }
        return true;
    }


    /**
     * Overloaded function with default source and dest
     * @return boolean
     */
    public boolean noPiecesInBetween(){
        return noPiecesInBetween(source, destination);
    }


}
