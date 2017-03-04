package xiangstudenthsadoyan.commonImplemenations;

import xiangqi.common.*;

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

    /**
     * Copy constructor. Takes in an XiangqiCoordinate and returns a XiangqiCoordinateImp
     * @param toCopy the input coordinate
     * @return output coordinate
     */
    public static XiangqiCoordinateImp copyConstructor(XiangqiCoordinate toCopy) {
        return new XiangqiCoordinateImp(toCopy.getRank(), toCopy.getFile());
    }

    /**
     * Creation method.
     * @param rank
     * @param file
     * @return new XiangqiCoordinateImp
     */
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


    /**
     * Returns distance to another point
     * @param c2 other point
     * @return distance as an int
     */
    public int distanceTo(XiangqiCoordinate c2){
        return (Math.abs(getRank() - c2.getRank())) + Math.abs(getFile() - c2.getFile());
    }

    /**
     * Returns whether the other point is orthogonal to this one
     * @param c2 other point
     * @return boolean
     */
    public boolean isOrthogonal(XiangqiCoordinate c2){
        return (getRank() == c2.getRank() || getFile() == c2.getFile());
    }

    /**
     * Returns whether the other point is diagonallyAdjacent to this one
     * @param c2 other point
     * @return boolean
     */
    public boolean isDiagonallyAdjacent(XiangqiCoordinate c2){
        return isDiagonal(c2) && (distanceTo(c2) == 2);
    }


    /**
     * Returns whether the other point is diagonal to this one
     * @param c2 other point
     * @return boolean
     */
    public boolean isDiagonal(XiangqiCoordinate c2){
        return (Math.abs(getRank() - c2.getRank()) == Math.abs(getFile() - c2.getFile()));

    }

    public boolean isLShape(XiangqiCoordinate c2){
        return Math.abs(Math.abs(getRank() - c2.getRank()) - Math.abs(getFile() - c2.getFile())) == 1;

    }

    public XiangqiCoordinateImp LOrthagonalSpot (XiangqiCoordinateImp c2){
        if(getRank() - c2.getRank() == 2){
            return new XiangqiCoordinateImp(getRank()-1 , getFile());
        }

        if(getRank() - c2.getRank() == -2){
            return new XiangqiCoordinateImp(getRank()+1 , getFile());
        }

        if(getFile() - c2.getFile() == 2){
            return new XiangqiCoordinateImp(getRank() , getFile() -1);
        }

        if(getFile() - c2.getFile() == -2){
            return new XiangqiCoordinateImp(getRank() , getFile()+1);
        }

        //Should never be reached
        return null;
    }
    /**
     * Returns whether given loc is between current point and dest
     * @param loc point to test
     * @param dest destination to test against
     * @return boolean
     */
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


    /**
     * Returns whether the other point is not backward to this one
     * @param aspect to test against
     * @param c2 other point
     * @return boolean
     */
    public boolean isNotBackWard(XiangqiCoordinateImp c2, XiangqiColor aspect){
        if(aspect == XiangqiColor.RED){
            return c2.getRank() >= getRank();
        } else {
            return c2.getRank() <= getRank();
        }
    }


    /**
     * Returns whether the other point is in front of this one
     * @param aspect to test against
     * @param c2 other point
     * @return boolean
     */
    public boolean isForward(XiangqiCoordinateImp c2, XiangqiColor aspect){
        if(aspect == XiangqiColor.RED) {
            return isNotBackWard(c2, aspect) && c2.getFile() == getFile();
        } else {
            return isNotBackWard(c2, aspect) && c2.getFile() == getFile();
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
