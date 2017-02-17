package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.XiangqiColor;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class ValidateFactory {



    private static Predicate<State> orthogonalStateValidator = (State s) -> s.isOrthogonal();
    private static Predicate<State> diagonalStateValidator = (State s) -> s.isDiagonal();
    private static Predicate<State> adjascentStateValidator = (State s) -> s.isAdjascent();
    private static Predicate<State> moveForwardStateValidator = (State s) -> s.isForward();
    private static Predicate<State> differentStateXiangqiCoordinateImpValidator = (State s) -> s.isDifferent();
    private static Predicate<State> diagonallyAdjascentStateValidator = (State s) -> s.isDiagonallyAdjascent();
    private static Predicate<State> inGeneralsPalaceStateValidator = (State s) -> s.inPalace();
    private static Predicate<State> pieceAtSource = (State s) -> s.pieceAtSource();
    private static Predicate<State> moveOnOwnPiece = (State s) -> s.moveOnOwnPiece();
    private static Predicate<State> noPiecesInBetween = (State s) -> s.noPiecesInBetween();
    private static Predicate<State> checkInBounds = (State s) -> s.isInBounds();


    public static List<Predicate<State>> makeStateValidators(XiangqiPieceImp piece){


        List<Predicate<State>> validators = new LinkedList<Predicate<State>>();

        validators.add(pieceAtSource);
        validators.add(moveOnOwnPiece);
        switch (piece.getPieceType()) {
            case SOLDIER:
                validators.add(noPiecesInBetween);
                validators.add(orthogonalStateValidator);
                validators.add(adjascentStateValidator);
                validators.add(moveForwardStateValidator);
                break;
            case ADVISOR:
                validators.add(diagonalStateValidator);
                validators.add(diagonallyAdjascentStateValidator);
                validators.add(noPiecesInBetween);
                break;
            case GENERAL:
                validators.add(orthogonalStateValidator);
                validators.add(adjascentStateValidator);
                validators.add(inGeneralsPalaceStateValidator);
                validators.add(noPiecesInBetween);
                break;
            case CHARIOT:
                validators.add(orthogonalStateValidator);
                validators.add(differentStateXiangqiCoordinateImpValidator);
                validators.add(noPiecesInBetween);
                break;
        }

        return validators;
    }
}
