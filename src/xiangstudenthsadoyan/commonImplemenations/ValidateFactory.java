package xiangstudenthsadoyan.commonImplemenations;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.XiangqiGameVersion;

import java.util.*;
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
    private static Predicate<State> distanceIsFour = (State s) -> s.getSource().distanceTo(s.getDestination()) == 4;
    private static Predicate<State> cantCrossRiver = (State s) -> s.notCrossingTheRiver();
    private static Predicate<State> isNotBackward = (State s) -> s.isNotBackward();
    //  private static Predicate<State> BetaInPalaceValidator = (State s) -> (c.getRank() == 1 || c.getRank() == 5) && c.getFile() >= 2 && c.getFile() <= 4;




    public static List<Predicate<State>> makeValidators(XiangqiPieceImp piece, State state, XiangqiGameVersion version){
        switch (version){
            case GAMMA_XQ:
                return makeGammaValidators(piece, state);
            case DELTA_XQ:
                return makeDeltaValidators(piece, state);
            default:
                return null;
        }
    }
    /**
     * Static method for making validators for BETA
     * @param piece to test validators on
     * @return List of Predicates
     */
    public static List<Predicate<State>> makeBetaValidators(XiangqiPieceImp piece){


        List<Predicate<State>> validators = addCommonValidators(piece);

        switch (piece.getPieceType()) {
            case SOLDIER:
                validators.add(moveForwardStateValidator);
        }

        return validators;
    }

    /**
     * Add validators that are common across versions
     * @param piece
     * @return List of Predicate
     */
    private static List<Predicate<State>> addCommonValidators(XiangqiPieceImp piece){

        List<Predicate<State>> validators = new LinkedList<Predicate<State>>();


        validators.add(checkInBounds);
        validators.add(pieceAtSource);
        validators.add(moveOnOwnPiece);
        switch (piece.getPieceType()) {
            case SOLDIER:
                validators.add(noPiecesInBetween);
                validators.add(orthogonalStateValidator);
                validators.add(adjascentStateValidator);
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

    /**
     * Static method for validators for GAMMA
     * @param piece
     * @param s
     * @return List of Predicate
     */
    public static List<Predicate<State>> makeGammaValidators(XiangqiPieceImp piece, State s){

        List<Predicate<State>> validators = addCommonValidators(piece);

        switch (piece.getPieceType()){
            case ADVISOR:
                validators.add(inGeneralsPalaceStateValidator);
                break;
            case ELEPHANT:
                validators.add(diagonalStateValidator);
                validators.add(distanceIsFour);
                validators.add(noPiecesInBetween);
                validators.add(cantCrossRiver);
                break;
            case SOLDIER:
                if(s.getBoard().isAcrossTheRiver(s.getSource(), s.getAspect())){
                    validators.add(isNotBackward);
                } else {
                    validators.add(moveForwardStateValidator);
                }
                break;
        }

        return validators;
    }

    public static List<Predicate<State>> makeDeltaValidators(XiangqiPieceImp piece, State s){

        List<Predicate<State>> validators = makeGammaValidators(piece, s);

        return validators;
    }

}
