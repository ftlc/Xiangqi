package xiangstudenthsadoyan;

import xiangqi.common.*;

/**
 * Created by gnomeftlc on 2/18/17.
 */
public class TestCoordinate implements XiangqiCoordinate {

        private final int rank;
        private final int file;

        public static XiangqiCoordinate makeCoordinate(int rank, int file){
            return new TestCoordinate(rank, file);
        }

        private TestCoordinate(int rank, int file)
        {
            this.rank = rank;
            this.file = file;
        }

        @Override
        public int getRank()
        {
            return rank;
        }

        @Override
        public int getFile()
        {
            return file;
        }

}
