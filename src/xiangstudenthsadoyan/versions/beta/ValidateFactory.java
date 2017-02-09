package xiangstudenthsadoyan.versions.beta;

import java.util.*;
import java.util.function.BiPredicate;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class ValidateFactory {

    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> orthogonalValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.isOrthogonal(c2);
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> diagonalValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.isDiagonal(c2);
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> adjacentValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.distanceTo(c2) == 1;
//    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> diagonalOrOrthogonalValidator =
//            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.isOrthogonal(c2) || c1.isDiagonal(c2);
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> differentXiangqiCoordinateImpValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> !c1.equals(c2);
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> moveForwardValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.isForward(c2);

    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> diagonallyAdjascentValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.isDiagonallyAdjascent(c2);
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> inGeneralsPalaceValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c2.inPalace();

    public static List<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>> makeValidators(XiangqiPieceImp piece){

        List<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>> validators =
                new LinkedList<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>>();
        switch (piece.getPieceType()) {
            case SOLDIER:
                validators.add(adjacentValidator);
                validators.add(orthogonalValidator);
                validators.add(moveForwardValidator);
                break;
            case ADVISOR:
                validators.add(diagonallyAdjascentValidator);
                validators.add(diagonalValidator);
                break;
            case GENERAL:
                validators.add(adjacentValidator);
                validators.add(orthogonalValidator);
                validators.add(inGeneralsPalaceValidator);
        }

        return validators;
    }
}
