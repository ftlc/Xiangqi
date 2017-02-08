package xiangstudenthsadoyan;

/**
 * Created by gnomeftlc on 2/6/17.
 */
import static org.junit.Assert.*;
import org.junit.Test;
import xiangqi.common.*;
import xiangqi.XiangqiGameFactory;

public class AlphaXiangqiTestCases {

    @Test
    public void factoryProducesAlphaXiangqiGame(){
        assertNotNull(XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.ALPHA_XQ));
    }
}
