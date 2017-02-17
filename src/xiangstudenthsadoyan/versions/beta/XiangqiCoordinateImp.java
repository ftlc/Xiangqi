package xiangstudenthsadoyan.versions.beta;

import xiangqi.common.*;

import java.util.Objects;

/**
 * Created by gnomeftlc on 2/9/17.
 */
public class XiangqiCoordinateImp implements XiangqiCoordinate {
    int rank;
    int file;
    private XiangqiCoordinateImp(int rank, int file){
        this.rank = rank;
        this.file = file;
    }

    public static XiangqiCoordinateImp copyConstructor(XiangqiCoordinate toCopy) {
        return new XiangqiCoordinateImp(toCopy.getRank(), toCopy.getFile());
    }
    public static XiangqiCoordinateImp makeCoordinate(int rank, int file){
        return new XiangqiCoordinateImp(rank, file);
    }
    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public int getFile() {
        return file;
    }

    public int distanceTo(XiangqiCoordinate c2){
        return (Math.abs(getRank() - c2.getRank())) + Math.abs(getFile() - c2.getFile());
    }
    public boolean isOrthogonal(XiangqiCoordinate c2){
        return (getRank() == c2.getRank() || getFile() == c2.getFile());
    }
    public boolean isDiagonallyAdjascent(XiangqiCoordinate c2){
        return isDiagonal(c2) && (distanceTo(c2) == 2);
    }

    public boolean inPalace(XiangqiColor currentTurn){
        if(currentTurn == XiangqiColor.RED) {
            return getRank() == 1 && getFile() >= 2 && getFile() <= 4;
        } else {
            return getRank() == 5 && getFile() >= 2 && getFile() <= 4;
        }
    }
    public boolean isDiagonal(XiangqiCoordinate c2){
        return (Math.abs(getRank() - c2.getRank()) == Math.abs(getFile() - c2.getFile()));

    }

    public boolean isLocationBetween(XiangqiCoordinateImp loc, XiangqiCoordinateImp dest) {


        int deltaX = dest.getFile() - getFile();
        int deltaY = dest.getRank() - getRank();

        int signX = Integer.signum(deltaX);
        int signY = Integer.signum(deltaY);

        deltaX = Math.abs(deltaX);
        deltaY = Math.abs(deltaY);

        int ex = 0;
        int tempRank = getRank();
        int tempFile = getFile();
        int i = 0;
        while(ex == 0) {
            tempRank += signY;
            tempFile += signX;

            if(XiangqiCoordinateImp.makeCoordinate(tempRank, tempFile).equals(dest)){
                return false;
            }
            if(XiangqiCoordinateImp.makeCoordinate(tempRank, tempFile).equals(loc)){
                return true;
            }

            i++;

            if(i > deltaX && i > deltaY){
                return true;
            }
        }

        return true;

    }


    public boolean isForward(XiangqiCoordinate c2, XiangqiColor color){
        if(color == XiangqiColor.RED) {
            return c2.getRank() > getRank() && c2.getFile() == getFile();
        } else {
            return c2.getRank() < getRank() && c2.getFile() == getFile();
        }
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof XiangqiCoordinate)) {
            return false;
        }
        XiangqiCoordinate other = (XiangqiCoordinate) obj;
        if (getFile() != other.getFile()) {
            return false;
        }
        if (getRank() != other.getRank()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + file;
        return result;
    }

    @Override
    public String toString(){
        return "Rank: " + getRank() + "\nFile: " + getFile() + "\n";
    }

}
