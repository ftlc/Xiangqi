package xiangstudenthsadoyan.versions.gamma;

import xiangqi.common.*;
import xiangstudenthsadoyan.commonImplemenations.*;

import java.util.concurrent.CompletionException;

/**
 * Created by gnomeftlc on 2/18/17.
 */


public class GammaXiangqiGame implements XiangqiGame {

    public static final int MOVELIMIT = 50;

    //Variables
    private Board board;
    private XiangqiColor currentTurn;
    private int moveNumber = 1;
    private State moveState;
    private GameLogic gameLogic;

    public GammaXiangqiGame(){
        board = Board.makeBoard(XiangqiGameVersion.GAMMA_XQ);
        currentTurn = XiangqiColor.RED;
        gameLogic = new GameLogic(XiangqiGameVersion.GAMMA_XQ);
    }


    @Override
    public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
        // Copy Constructors
        XiangqiCoordinateImp newSource = XiangqiCoordinateImp.copyConstructor(source);
        XiangqiCoordinateImp newDest = XiangqiCoordinateImp.copyConstructor(destination);

        // Standardize Coordinates
        if(currentTurn == XiangqiColor.BLACK){
            newSource = gameLogic.convertCoordinate(newSource, board);
            newDest = gameLogic.convertCoordinate(newDest, board);
        }
        //Check Piece correct color
        if(board.getPieceAt(newSource).getColor() == gameLogic.getOppositeColor(currentTurn)){
            gameLogic.setMoveMessage("Piece Wrong Color");
            return MoveResult.ILLEGAL;
        }
        //Create Official State
        moveState = new State(board, newSource, newDest, currentTurn);
        if(!gameLogic.runAndTestGeneral(moveState)) {
            return MoveResult.ILLEGAL;
        }
        //Update the board
        moveState.movePiece(newSource, newDest);
        moveNumber++;
        //Check for endgame
        if(gameOver(moveState)) {
            return gameLogic.convertColorToMoveWin(currentTurn);
        }
        //Check for draw
        if(moveNumber > MOVELIMIT){
            return MoveResult.DRAW;
        }
        //Update current turn
        switchCurrentTurn();
        return MoveResult.OK;
    }


    /**
     * Returns true if the opposite side has no valid moves
     * @param theState to check
     * @return boolean
     */
    public boolean gameOver(State theState){
        return !gameLogic.anyValidMoves(theState);
    }




    /**
     * Switch current turn
     */
    private void switchCurrentTurn(){
        currentTurn = gameLogic.getOppositeColor(currentTurn);
    }


    @Override
    public String getMoveMessage() {
        return gameLogic.getMoveMessage();
    }

    @Override
    public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
        XiangqiCoordinateImp temp = XiangqiCoordinateImp.copyConstructor(where);

        if(!board.isInBounds(temp)){
            throw new CompletionException(new Throwable());
        }
        if(aspect == XiangqiColor.BLACK) {
            temp = gameLogic.convertCoordinate(temp, board);
        }
        return board.getPieceAt(temp);
    }


}
