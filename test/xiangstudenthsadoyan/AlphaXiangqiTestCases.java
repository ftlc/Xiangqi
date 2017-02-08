package xiangstudenthsadoyan;

/**
 * Created by gnomeftlc on 2/6/17.
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import sun.awt.X11.XIconSize;
import xiangqi.common.*;
import xiangqi.XiangqiGameFactory;

public class AlphaXiangqiTestCases {

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
        game=XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.ALPHA_XQ);
    }
    @Test
    public void factoryProducesAlphaXiangqiGame(){
        assertNotNull(game);
    }

    @Test
    public void redMakesFirstMove(){
        assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1,2)));
    }

    @Test
    public void blackMakesSecondMove(){
        game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1,2));
        assertEquals(MoveResult.RED_WINS, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1,2)));
    }

    @Test
    public void tryToMoveToInvalidLocation(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2,1)));
        assertTrue(game.getMoveMessage().length() >= 1);
    }

    @Test
    public void tryToMoveFromInvalidLocation(){
        assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1,2)));
        assertTrue(game.getMoveMessage().length() >= 1);

    }

    @Test
    public void getPieceAtReturnsNoneNone(){
        final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
        assertEquals(XiangqiPieceType.NONE, p.getPieceType());
        assertEquals(XiangqiColor.NONE, p.getColor());
    }







}
