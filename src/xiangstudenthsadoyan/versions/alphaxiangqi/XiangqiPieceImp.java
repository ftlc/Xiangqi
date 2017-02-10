package xiangstudenthsadoyan.versions.alphaxiangqi;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * Created by gnomeftlc on 2/7/17.
 */
public class XiangqiPieceImp implements XiangqiPiece {
    private final XiangqiPieceType pieceType;
    private final XiangqiColor color;
    private XiangqiPieceImp(XiangqiPieceType pieceType, XiangqiColor color){
        this.pieceType = pieceType;
        this.color = color;
    }
    public static XiangqiPieceImp makePiece(XiangqiPieceType pieceType, XiangqiColor color){
        return new XiangqiPieceImp(pieceType, color);
    }
    @Override
    public XiangqiColor getColor() {
        return color;
    }

    @Override
    public XiangqiPieceType getPieceType() {
        return pieceType;
    }
}
