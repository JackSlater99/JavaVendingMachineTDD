import Coins.Coin;
import Coins.CoinType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinTest {

    Coin coin;
    Coin invalidCoin;

    @Before
    public void before(){
        coin = new Coin(CoinType.FIFTYPENCE);
        invalidCoin = new Coin(CoinType.ONEPENCE);
    }

    @Test
    public void hasCoinType() {
        assertEquals(CoinType.FIFTYPENCE, coin.getCoinType());
    }

    @Test
    public void canGetCoinValue(){
        assertEquals(0.50, coin.getCoinType().value, 0.01);
    }

    @Test
    public void hasValidity() {
        assertEquals(true, coin.getCoinType().isValid);
    }

    @Test
    public void notValid() {
        assertEquals(false, invalidCoin.getCoinType().isValid);
    }
}
