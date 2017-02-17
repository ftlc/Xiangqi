package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPieceType;

import java.util.Set;

/**
 * Created by gnomeftlc on 2/16/17.
 */
public class State {
    private Board board;
    private XiangqiCoordinateImp source;
    private XiangqiCoordinateImp destination;
    private String moveMessage;
    private XiangqiColor currentTurn;

    public State(Board board, XiangqiCoordinateImp source, XiangqiCoordinateImp dest, XiangqiColor currentTurn){
       this.board = board;
       this.source = source;
       this.destination = dest;
       this.currentTurn = currentTurn;
    }


    public void setMoveMessage(String moveMessage) {
        this.moveMessage = moveMessage;
    }

    public String getMoveMessage() {
        return moveMessage;
    }

    public boolean pieceAtSource(){
        if(board.getPieceAt(source).getPieceType() == XiangqiPieceType.NONE){
            setMoveMessage("No Piece At Source");
            return false;
        }
        return true;
    }

    public boolean moveOnOwnPiece(){
        if(board.getPieceAt(destination).getColor() == board.getPieceAt(source).getColor()
                && !destination.equals(source)){
            setMoveMessage("Can't Capture Your Own Piece");
            return false;
        }
        return true;
    }

    public boolean isInBounds(){
        if(!board.isInBounds(source) || !board.isInBounds(destination)){
            setMoveMessage("OUT OF BOUNDS");
            return false;
        }
        return true;
    }

    public boolean isDifferent(){
        if(source.equals(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    public boolean inPalace(){
        if(!destination.inPalace(currentTurn)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }
    public boolean isDiagonallyAdjascent(){
        if(!source.isDiagonallyAdjascent(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    public boolean isDiagonal(){
        if(!source.isDiagonal(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    public boolean isAdjascent(){
        if(!(source.distanceTo(destination) == 1)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }

    public boolean isForward(){
        if(!(source.isForward(destination, currentTurn))){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;
    }
    public boolean isOrthogonal(){
        if(!source.isOrthogonal(destination)){
            setMoveMessage("Illegal " + board.getPieceAt(source) + " Move");
            return false;
        }
        return true;

    }

    public boolean noPiecesInBetween(){

        Set<XiangqiCoordinateImp> occupied = board.getAllOccupiedLocations();
        for(XiangqiCoordinateImp o: occupied){
            if(o.equals(source) || o.equals(destination)){
                continue;
            }

            if(source.isLocationBetween(o,destination)){
                setMoveMessage("Can't Jump Over A Piece");
                return false;
            }

        }

        return true;
    }


}
