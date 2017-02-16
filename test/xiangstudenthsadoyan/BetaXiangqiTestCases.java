package xiangstudenthsadoyan;

import org.junit.Before;
import org.junit.Test;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;

import static org.junit.Assert.*;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class BetaXiangqiTestCases {


     static class TestCoordinate implements XiangqiCoordinate {

        int file;
        int rank;
        TestCoordinate(int rank, int file) {
            this.file = file;
            this.rank = rank;
        }
        public static XiangqiCoordinate makeCoordinate(int rank, int file){
            return new TestCoordinate(rank, file);
        }

        @Override
        public int getRank() {
            return rank;
        }

        @Override
        public int getFile() {
            return file;
        }
    }




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
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(2, 2), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.NONE, p.getPieceType());
        assertEquals(XiangqiColor.NONE, p.getColor());
    }
    @Test //3
    public void validateRedGeneralAt13(){
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
        assertEquals(XiangqiColor.RED, p.getColor());
    }
    @Test //4
    public void validateBlackGeneralAt13(){
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
        assertEquals(XiangqiColor.BLACK, p.getColor());
    }

    @Test //5
    public void validateStartingRedPiecesInCorrectSpot() {

        final XiangqiPiece general = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.GENERAL, general.getPieceType());
        assertEquals(XiangqiColor.RED, general.getColor());

        final XiangqiPiece chariot = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.CHARIOT, chariot.getPieceType());
        assertEquals(XiangqiColor.RED, chariot.getColor());

        final XiangqiPiece advisor = game.getPieceAt(TestCoordinate.makeCoordinate(1, 2), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.ADVISOR, advisor.getPieceType());
        assertEquals(XiangqiColor.RED, advisor.getColor());

        final XiangqiPiece advisor2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.ADVISOR, advisor2.getPieceType());
        assertEquals(XiangqiColor.RED, advisor2.getColor());

        final XiangqiPiece chariot2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.CHARIOT, chariot2.getPieceType());
        assertEquals(XiangqiColor.RED, chariot2.getColor());

        final XiangqiPiece soldier = game.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.SOLDIER, soldier.getPieceType());
        assertEquals(XiangqiColor.RED, soldier.getColor());
    }


    @Test //6
    public void validateStartingBlackPiecesInCorrectSpot() {

        final XiangqiPiece general = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.GENERAL, general.getPieceType());
        assertEquals(XiangqiColor.BLACK, general.getColor());

        final XiangqiPiece chariot = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.CHARIOT, chariot.getPieceType());
        assertEquals(XiangqiColor.BLACK, chariot.getColor());

        final XiangqiPiece advisor = game.getPieceAt(TestCoordinate.makeCoordinate(1, 2), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.ADVISOR, advisor.getPieceType());
        assertEquals(XiangqiColor.BLACK, advisor.getColor());

        final XiangqiPiece advisor2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.ADVISOR, advisor2.getPieceType());
        assertEquals(XiangqiColor.BLACK, advisor2.getColor());

        final XiangqiPiece chariot2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.CHARIOT, chariot2.getPieceType());
        assertEquals(XiangqiColor.BLACK, chariot2.getColor());

        final XiangqiPiece soldier = game.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.BLACK);
        assertEquals(XiangqiPieceType.SOLDIER, soldier.getPieceType());
        assertEquals(XiangqiColor.BLACK, soldier.getColor());
    }

    @Test //7
    public void testInvalidCoordinateOutOfBounds() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(0, 1), TestCoordinate.makeCoordinate(1, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(7, 0), TestCoordinate.makeCoordinate(1, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(0, 0), TestCoordinate.makeCoordinate(1, 7)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());

        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(0, 0), TestCoordinate.makeCoordinate(0, 1)));
        assertEquals("OUT OF BOUNDS", game.getMoveMessage());
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
        assertEquals(XiangqiColor.RED, game.getPieceAt(TestCoordinate.makeCoordinate(3, 5), XiangqiColor.RED).getColor());

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
    public void validateCheckmateIfKingUnderAttackAndCantMove(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(5, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 2)));

        assertEquals(MoveResult.RED_WINS, game.makeMove(TestCoordinate.makeCoordinate(5, 1), TestCoordinate.makeCoordinate(5, 2)));
    }

 /*   @Test
    public void validateNotCheckmateIfKingCanRunAway(){

        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(5, 4), TestCoordinate.makeCoordinate(4, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 3), TestCoordinate.makeCoordinate(3, 3)));
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(3, 3)));
    }

    */






}
