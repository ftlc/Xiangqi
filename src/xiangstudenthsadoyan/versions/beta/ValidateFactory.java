package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.XiangqiCoordinate;

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
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> diagonalOrOrthogonalValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> c1.isOrthogonal(c2) || c1.isDiagonal(c2);
    private static BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp> differentXiangqiCoordinateImpValidator =
            (XiangqiCoordinateImp c1, XiangqiCoordinateImp c2) -> !c1.equals(c2);

    public static List<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>> makeValidators(XiangqiPieceImp piece){

        List<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>> validators =
                new LinkedList<BiPredicate<XiangqiCoordinateImp, XiangqiCoordinateImp>>();
        switch (piece.getPieceType()) {
            case GENERAL:
                validators.add(adjacentValidator);
                break;
        }

        return validators;
    }
}
