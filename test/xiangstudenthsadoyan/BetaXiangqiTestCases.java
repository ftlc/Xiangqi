package xiangstudenthsadoyan;

import org.junit.Before;
import org.junit.Test;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;
import xiangstudenthsadoyan.versions.beta.XiangqiCoordinateImp;

import static org.junit.Assert.*;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class BetaXiangqiTestCases {

    private XiangqiGame game;

    @Before
    public void setup() {
        game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
    }

    @Test //1
    public void factoryProducesBetaXiangqiGame() {
        assertNotNull(game);
    }

    @Test //2
    public void validateEmptyLocationReturnsEmpty(){
        final XiangqiPiece p = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(2, 2), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.NONE, p.getPieceType());
        assertEquals(XiangqiColor.NONE, p.getColor());
    }
    @Test //3
    public void validateRedGeneralAt13(){
        final XiangqiPiece p = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 3), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
        assertEquals(XiangqiColor.RED, p.getColor());
    }
    @Test //4
    public void validateBlackGeneralAt13(){
        final XiangqiPiece p = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 3), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
        assertEquals(XiangqiColor.BLACK, p.getColor());
    }

    @Test //5
    public void validateStartingRedPiecesInCorrectSpot() {

        final XiangqiPiece general = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 3), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.GENERAL, general.getPieceType());
        assertEquals(XiangqiColor.RED, general.getColor());

        final XiangqiPiece chariot = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 1), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.CHARIOT, chariot.getPieceType());
        assertEquals(XiangqiColor.RED, chariot.getColor());

        final XiangqiPiece advisor = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 2), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.ADVISOR, advisor.getPieceType());
        assertEquals(XiangqiColor.RED, advisor.getColor());

        final XiangqiPiece advisor2 = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 4), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.ADVISOR, advisor2.getPieceType());
        assertEquals(XiangqiColor.RED, advisor2.getColor());

        final XiangqiPiece chariot2 = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 5), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.CHARIOT, chariot2.getPieceType());
        assertEquals(XiangqiColor.RED, chariot2.getColor());

        final XiangqiPiece soldier = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(2, 3), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.SOLDIER, soldier.getPieceType());
        assertEquals(XiangqiColor.RED, soldier.getColor());
    }


    @Test //6
    public void validateStartingBlackPiecesInCorrectSpot() {

        final XiangqiPiece general = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 3), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.GENERAL, general.getPieceType());
        assertEquals(XiangqiColor.BLACK, general.getColor());

        final XiangqiPiece chariot = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 1), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.CHARIOT, chariot.getPieceType());
        assertEquals(XiangqiColor.BLACK, chariot.getColor());

        final XiangqiPiece advisor = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 2), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.ADVISOR, advisor.getPieceType());
        assertEquals(XiangqiColor.BLACK, advisor.getColor());

        final XiangqiPiece advisor2 = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 4), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.ADVISOR, advisor2.getPieceType());
        assertEquals(XiangqiColor.BLACK, advisor2.getColor());

        final XiangqiPiece chariot2 = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(1, 5), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.CHARIOT, chariot2.getPieceType());
        assertEquals(XiangqiColor.BLACK, chariot2.getColor());

        final XiangqiPiece soldier = game.getPieceAt(XiangqiCoordinateImp.makeCoordinate(2, 3), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.SOLDIER, soldier.getPieceType());
        assertEquals(XiangqiColor.BLACK, soldier.getColor());
    }

    @Test //7
    public void testInvalidCoordinateOutOfBounds() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(XiangqiCoordinateImp.makeCoordinate(0, 1), XiangqiCoordinateImp.makeCoordinate(1, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(XiangqiCoordinateImp.makeCoordinate(7, 0), XiangqiCoordinateImp.makeCoordinate(1, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(XiangqiCoordinateImp.makeCoordinate(0, 0), XiangqiCoordinateImp.makeCoordinate(1, 7)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(XiangqiCoordinateImp.makeCoordinate(0, 0), XiangqiCoordinateImp.makeCoordinate(0, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());
    }

    @Test //8
    public void validateIllegalPawnMove(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(XiangqiCoordinateImp.makeCoordinate(2,3), XiangqiCoordinateImp.makeCoordinate(2, 4)));
        assertEquals("Illegal Pawn Move", game.getMoveMessage());
    }

    @Test //9
    public void validateLegalPawnMove(){
        assertEquals(MoveResult.OK, game.makeMove(XiangqiCoordinateImp.makeCoordinate(2,3), XiangqiCoordinateImp.makeCoordinate(3, 3)));
    }
}
