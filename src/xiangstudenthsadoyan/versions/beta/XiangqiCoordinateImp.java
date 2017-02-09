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
    public static XiangqiCoordinate makeCoordinate(int rank, int file){
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

    public boolean isDiagonal(XiangqiCoordinate c2){
        return (Math.abs(getRank() - c2.getRank()) == Math.abs(getFile() - c2.getFile()));

    }

    public boolean isForward(XiangqiCoordinate c2){
        return c2.getRank() > getRank() && c2.getFile() == getFile();
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
    public String toString(){
        return "Rank: " + getRank() + "\nFile: " + getFile() + "\n";
    }

}
