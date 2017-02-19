package xiangstudenthsadoyan;

import xiangqi.common.*;

/**
 * Created by gnomeftlc on 2/18/17.
 */
class TestPiece implements XiangqiPiece
{
    private final XiangqiColor color;
    private final XiangqiPieceType pieceType;

    private TestPiece(XiangqiPieceType pieceType, XiangqiColor color)
    {
        this.pieceType = pieceType;
        this.color = color;
    }

    public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color){
        return new TestPiece(pieceType, color);
    }

    /*
     * @see xiangqi.common.XiangqiPiece#getColor()
     */
    @Override
    public XiangqiColor getColor()
    {
        return color;
    }

    /*
     * @see xiangqi.common.XiangqiPiece#getPieceType()
     */
    @Override
    public XiangqiPieceType getPieceType()
    {
        return pieceType;
    }

    @Override
    public boolean equals(Object obj)
    {
        XiangqiPiece other = (XiangqiPiece) obj;
        if (color != other.getColor())
            return false;
        if (pieceType != other.getPieceType())
            return false;
        return true;
    }
}

