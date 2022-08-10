import Products.Crisps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    Crisps crisps;

    @Before
    public void before(){
        crisps = new Crisps("Walkers", 0.50, "A1");
    }

    @Test
    public void hasName(){
        assertEquals("Walkers", crisps.getName());
    }

    @Test
    public void hasPrice(){
        assertEquals(0.50, crisps.getPrice(), 0.01);
    }

    @Test
    public void hasCode(){
        assertEquals("A1", crisps.getCode());
    }
}
