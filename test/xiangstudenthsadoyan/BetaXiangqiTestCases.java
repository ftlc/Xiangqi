package xiangstudenthsadoyan;

import org.junit.Before;
import org.junit.Test;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;

import static org.junit.Assert.*;
import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;
import static xiangstudenthsadoyan.TestPiece.makePiece;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class BetaXiangqiTestCases {



    private static XiangqiCoordinate c11 = TestCoordinate.makeCoordinate(1, 1),
            c12 = TestCoordinate.makeCoordinate(1, 2), c13 = TestCoordinate.makeCoordinate(1, 3),
            c14 = TestCoordinate.makeCoordinate(1, 4), c15 = TestCoordinate.makeCoordinate(1, 5),
            c21 = TestCoordinate.makeCoordinate(2, 1), c22 = TestCoordinate.makeCoordinate(2, 2),
            c23 = TestCoordinate.makeCoordinate(2, 3), c24 = TestCoordinate.makeCoordinate(2, 4),
            c25 = TestCoordinate.makeCoordinate(2, 5), c31 = TestCoordinate.makeCoordinate(3, 1),
            c32 = TestCoordinate.makeCoordinate(3, 2), c33 = TestCoordinate.makeCoordinate(3, 3),
            c34 = TestCoordinate.makeCoordinate(3, 4), c35 = TestCoordinate.makeCoordinate(3, 5),
            c41 = TestCoordinate.makeCoordinate(4, 1), c42 = TestCoordinate.makeCoordinate(4, 2),
            c43 = TestCoordinate.makeCoordinate(4, 3), c44 = TestCoordinate.makeCoordinate(4, 4),
            c45 = TestCoordinate.makeCoordinate(4, 5), c51 = TestCoordinate.makeCoordinate(5, 1),
            c52 = TestCoordinate.makeCoordinate(5, 2), c53 = TestCoordinate.makeCoordinate(5, 3),
            c54 = TestCoordinate.makeCoordinate(5, 4), c55 = TestCoordinate.makeCoordinate(5, 5);

    private static XiangqiPiece noPiece =
            makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE),
            redChariot = makePiece(CHARIOT, RED),
            redAdvisor = makePiece(ADVISOR, RED),
            redGeneral = makePiece(GENERAL, RED),
            redSoldier = makePiece(SOLDIER, RED),
            blackChariot = makePiece(CHARIOT, BLACK),
            blackAdvisor = makePiece(ADVISOR, BLACK),
            blackGeneral = makePiece(GENERAL, BLACK),
            blackSoldier = makePiece(SOLDIER, BLACK);
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
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(2, 2), RED);
        assertEquals(XiangqiPieceType.NONE, p.getPieceType());
        assertEquals(XiangqiColor.NONE, p.getColor());
    }
    @Test //3
    public void validateRedGeneralAt13(){
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), RED);
        assertEquals(GENERAL, p.getPieceType());
        assertEquals(RED, p.getColor());
    }
    @Test //4
    public void validateBlackGeneralAt13(){
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), BLACK);
        assertEquals(GENERAL, p.getPieceType());
        assertEquals(BLACK, p.getColor());
    }

    @Test //5
    public void validateStartingRedPiecesInCorrectSpot() {

        final XiangqiPiece general = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), RED);
        assertEquals(GENERAL, general.getPieceType());
        assertEquals(RED, general.getColor());

        final XiangqiPiece chariot = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), RED);
        assertEquals(CHARIOT, chariot.getPieceType());
        assertEquals(RED, chariot.getColor());

        final XiangqiPiece advisor = game.getPieceAt(TestCoordinate.makeCoordinate(1, 2), RED);
        assertEquals(ADVISOR, advisor.getPieceType());
        assertEquals(RED, advisor.getColor());

        final XiangqiPiece advisor2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), RED);
        assertEquals(ADVISOR, advisor2.getPieceType());
        assertEquals(RED, advisor2.getColor());

        final XiangqiPiece chariot2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), RED);
        assertEquals(CHARIOT, chariot2.getPieceType());
        assertEquals(RED, chariot2.getColor());

        final XiangqiPiece soldier = game.getPieceAt(TestCoordinate.makeCoordinate(2, 3), RED);
        assertEquals(SOLDIER, soldier.getPieceType());
        assertEquals(RED, soldier.getColor());
    }


    @Test //6
    public void validateStartingBlackPiecesInCorrectSpot() {

        final XiangqiPiece general = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), BLACK);
        assertEquals(GENERAL, general.getPieceType());
        assertEquals(BLACK, general.getColor());

        final XiangqiPiece chariot = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), BLACK);
        assertEquals(CHARIOT, chariot.getPieceType());
        assertEquals(BLACK, chariot.getColor());

        final XiangqiPiece advisor = game.getPieceAt(TestCoordinate.makeCoordinate(1, 2), BLACK);
        assertEquals(ADVISOR, advisor.getPieceType());
        assertEquals(BLACK, advisor.getColor());

        final XiangqiPiece advisor2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), BLACK);
        assertEquals(ADVISOR, advisor2.getPieceType());
        assertEquals(BLACK, advisor2.getColor());

        final XiangqiPiece chariot2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), BLACK);
        assertEquals(CHARIOT, chariot2.getPieceType());
        assertEquals(BLACK, chariot2.getColor());

        final XiangqiPiece soldier = game.getPieceAt(TestCoordinate.makeCoordinate(2, 3), BLACK);
        assertEquals(SOLDIER, soldier.getPieceType());
        assertEquals(BLACK, soldier.getColor());
    }

    @Test //7
    public void testInvalidCoordinateOutOfBounds() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(0, 1), TestCoordinate.makeCoordinate(1, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(7, 0), TestCoordinate.makeCoordinate(1, 1)));
        //      assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(0, 0), TestCoordinate.makeCoordinate(1, 7)));
        //       assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(0, 0), TestCoordinate.makeCoordinate(0, 1)));
        //       assertEquals("OUT OF BOUNDS", game.getMoveMessage());
    }

    @Test
    public void validateGeneralCantLeavePalace(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(2, 3)));
    }
    @Test //8
    public void validateMoveOnEmptyLocation(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2,2), TestCoordinate.makeCoordinate(3, 3)));
        assertEquals("No Piece At Source", game.getMoveMessage());
    }
    @Test //9
    public void validateIllegalPawnMoves(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(2, 4)));
        assertEquals("Illegal Soldier Move", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(2, 3)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(4, 3)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(1, 3)));
    }

    @Test //10
    public void validateLegalPawnMoveRed(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(3, 3)));
    }

    @Test //11
    public void validateLegalPawnMoveBlack(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(3, 3)));
    }

    @Test //12
    public void validateIllegalAdvisorMovesRed(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,2), TestCoordinate.makeCoordinate(1, 2)));
        assertEquals("Illegal Advisor Move", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,2), TestCoordinate.makeCoordinate(1, 3)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,2), TestCoordinate.makeCoordinate(2, 2)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,2), TestCoordinate.makeCoordinate(3, 4)));
    }

    @Test //13
    public void validateLegalAdvisorMovesRed(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
    }

    @Test //14
    public void validateIllegalAdvisorMovesBlack(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,4), TestCoordinate.makeCoordinate(1, 4)));
        assertEquals("Illegal Advisor Move", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,4), TestCoordinate.makeCoordinate(1, 3)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,4), TestCoordinate.makeCoordinate(2, 4)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,4), TestCoordinate.makeCoordinate(3, 2)));
    }

    @Test //15
    public void validateIllegalGeneralMove(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,3), TestCoordinate.makeCoordinate(2, 3)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,3), TestCoordinate.makeCoordinate(2, 4)));

    }

    @Test //15
    public void validateLegalGeneralMove(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,3), TestCoordinate.makeCoordinate(1, 2)));
    }


    @Test
    public void validateIllegalChariotMove(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(1, 1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(2, 2)));
    }

    @Test
    public void validateLegalChariotMove(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
    }

    @Test
    public void validateMoveIsSaved(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3,1), TestCoordinate.makeCoordinate(3, 3)));
    }

    @Test
    public void validateCorrectTurnOrder(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3,5), TestCoordinate.makeCoordinate(3, 3)));
        assertEquals("Piece Wrong Color", game.getMoveMessage());
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3,1), TestCoordinate.makeCoordinate(3, 3)));
    }

    @Test
    public void validateDrawAfterTenTurns(){

        for(int i = 0; i < 4; i++) {
            game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1));
            game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1));
            game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(1, 1));
            game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(1, 1));
        }

        assertEquals(MoveResult.OK,game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK,game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK,game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(1, 1)));
        assertEquals(MoveResult.DRAW, game.makeMove(TestCoordinate.makeCoordinate(3,1), TestCoordinate.makeCoordinate(1, 1)));
    }

    @Test
    public void validateCapture(){

        game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1));
        game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(3, 5)));
        assertEquals(RED, game.getPieceAt(TestCoordinate.makeCoordinate(3, 5), RED).getColor());

    }

    @Test
    public void validateCantCaptureOwnPiece(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(1, 2)));
        assertEquals("Can't Capture Your Own Piece", game.getMoveMessage());
    }

    @Test
    public void validateCantJumpOverPieces(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3,1), TestCoordinate.makeCoordinate(3, 3)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3,1), TestCoordinate.makeCoordinate(3, 4)));
        assertEquals("Can't Jump Over A Piece", game.getMoveMessage());
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3,1), TestCoordinate.makeCoordinate(3, 2)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3,3), TestCoordinate.makeCoordinate(3, 5)));
    }

    @Test
    public void validateBlackPawnCantMoveBac(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(5, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 2)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 5)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(1, 3)));
    }

    @Test
    public void validateCheckmateIfKingUnderAttackAndCantMove(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(5, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 2)));

        assertEquals(MoveResult.RED_WINS, game.makeMove(TestCoordinate.makeCoordinate(5, 1), TestCoordinate.makeCoordinate(5, 2)));
    }
    @Test
    public void validateCheckmateIfKingUnderAttackAndCantMoveBlack(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(5, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 2)));

        assertEquals(MoveResult.BLACK_WINS, game.makeMove(TestCoordinate.makeCoordinate(5, 1), TestCoordinate.makeCoordinate(5, 2)));
    }
    @Test
    public void validateNotCheckmateIfKingCanRunAway(){

        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(3, 3)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(3, 3)));
    }

    @Test
    public void validateFlyingGeneralsMove(){
        assertEquals(MoveResult.OK, game.makeMove(c23, c33));
        assertEquals(MoveResult.OK, game.makeMove(c23, c33));
        assertEquals(MoveResult.OK, game.makeMove(c12, c23));
        assertEquals(MoveResult.OK, game.makeMove(c14, c23));
        assertEquals(MoveResult.OK, game.makeMove(c13, c12));
        assertEquals(MoveResult.RED_WINS, game.makeMove(c13, c14));
    }


    @Test
    public void validateCantExposeGeneralToCheck(){
        assertEquals(MoveResult.OK, game.makeMove(c23, c33));
        assertEquals(MoveResult.OK, game.makeMove(c23, c33));
        assertEquals(MoveResult.OK, game.makeMove(c12, c23));
        assertEquals(MoveResult.OK, game.makeMove(c12, c23));
        assertEquals(MoveResult.OK, game.makeMove(c11, c31));
        assertEquals(MoveResult.OK, game.makeMove(c11, c31));
        assertEquals(MoveResult.OK, game.makeMove(c31, c33));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(c23, c12));
    }

    @Test
    public void validateNoCheckmateIfPieceCanBlockOrTake(){
        assertEquals(MoveResult.OK, game.makeMove(c23, c33));
        assertEquals(MoveResult.OK, game.makeMove(c23, c33));
        assertEquals(MoveResult.OK, game.makeMove(c11, c31));
        assertEquals(MoveResult.OK, game.makeMove(c33, c43));
    }









}
