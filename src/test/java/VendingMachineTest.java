import Coins.Coin;
import Coins.CoinType;
import Products.Cola;
import Products.Crisps;
import Products.Product;
import Products.Sweet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {

    VendingMachine vendingMachine;
    Coin one;
    Coin two;
    Coin five;
    Coin ten;
    Coin twenty;
    Coin fifty;
    Coin onePound;
    Coin twoPound;
    Product crisps;
    Product cola;
    Product sweet;

    @Before
    public void before() {
        vendingMachine = new VendingMachine();
        one = new Coin(CoinType.ONEPENCE);
        two = new Coin(CoinType.TWOPENCE);
        five = new Coin(CoinType.FIVEPENCE);
        ten = new Coin(CoinType.TENPENCE);
        twenty = new Coin(CoinType.TWENTYPENCE);
        fifty = new Coin(CoinType.FIFTYPENCE);
        onePound = new Coin(CoinType.ONEPOUND);
        twoPound = new Coin(CoinType.TWOPOUND);
        crisps = new Crisps("Walkers", 0.50, "A1");
        cola = new Cola("Cola", 1.00, "B1");
        sweet = new Sweet("Mars Bar", 0.65, "C1");
    }

    @Test
    public void reserveStartsWithNoCoins(){
        assertEquals(0, vendingMachine.getCoinReserve());
    }

    @Test
    public void productListStartsEmpty(){
        assertEquals(0, vendingMachine.getProductStock());
    }

    @Test
    public void balanceStartsAtZero(){
        assertEquals(0, vendingMachine.getCurrentBalance(), 0.01);
    }

    @Test
    public void canAddCoinToReserve(){
        vendingMachine.addCoinToReserve(ten);
        assertEquals(1, vendingMachine.getCoinReserve());
    }

    @Test
    public void canAddProducts(){
        vendingMachine.addProduct(cola);
        assertEquals(1, vendingMachine.getProductStock());
    }

    @Test
    public void canAddToBalance(){
        vendingMachine.addCoinToBalance(onePound);
        assertEquals(1.00, vendingMachine.getCurrentBalance(), 0.01);
    }

    @Test
    public void cantAddSmallChangeToBalance(){
        vendingMachine.addCoinToBalance(two);
        assertEquals(0, vendingMachine.getCurrentBalance(), 0.01);
    }

    @Test
    public void totalReserveStartsAtZero(){
        assertEquals(0, vendingMachine.calculateTotalReserveCash(), 0.01);
    }

    @Test
    public void canTotalReserve(){
        vendingMachine.addCoinToReserve(ten);
        vendingMachine.addCoinToReserve(onePound);
        vendingMachine.addCoinToReserve(fifty);
        assertEquals(1.60, vendingMachine.calculateTotalReserveCash(), 0.01);
    }

    @Test
    public void canReturnBalance(){
        vendingMachine.addCoinToBalance(onePound);
        vendingMachine.returnBalance();
        assertEquals(0, vendingMachine.getCurrentBalance(), 0.01);
    }

    @Test
    public void canCheckCode(){
        vendingMachine.addProduct(crisps);
        assertEquals(crisps, vendingMachine.getProductFromCode("A1"));
    }

    @Test
    public void canCheckCodeIsInvalid(){
        vendingMachine.addProduct(crisps);
        assertNull( vendingMachine.getProductFromCode("Wrong Code"));
    }

    @Test
    public void checkThereIsEnoughCashInBalance(){
        vendingMachine.addCoinToBalance(onePound);
        vendingMachine.addProduct(crisps);
        Product product = vendingMachine.getProductFromCode("A1");
        vendingMachine.checkEnoughBalance(product);
        assertEquals("Can Purchase", vendingMachine.checkEnoughBalance(product));
        assertTrue(vendingMachine.getCanPurchase());
    }

    @Test
    public void checkThereIsNotEnoughCashInBalance(){
        vendingMachine.addCoinToBalance(twenty);
        vendingMachine.addProduct(crisps);
        Product product = vendingMachine.getProductFromCode("A1");
        vendingMachine.checkEnoughBalance(product);
        assertEquals("Required: 0.30", vendingMachine.checkEnoughBalance(product));
        assertFalse(vendingMachine.getCanPurchase());
    }

    @Test
    public void canGetChangeDue(){
        vendingMachine.addCoinToBalance(onePound);
        vendingMachine.addProduct(crisps);
        Product product = vendingMachine.getProductFromCode("A1");
        vendingMachine.checkEnoughBalance(product);
        assertEquals("Change: 0.50", vendingMachine.calculateChange(product));
    }

    @Test
    public void canBuyProduct(){
        vendingMachine.addCoinToBalance(onePound);
        vendingMachine.addProduct(crisps);
        assertEquals("Change: 0.50", vendingMachine.buyProduct("A1"));
        assertEquals(0, vendingMachine.getProductStock());
        assertEquals(1, vendingMachine.getCoinReserve());
        assertEquals(1.00, vendingMachine.calculateTotalReserveCash(), 0.01);
        assertFalse(vendingMachine.getCanPurchase());
    }
}
