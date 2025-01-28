import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSalesTrackerTest {
    private ProductSalesTracker tracker1;
    private ProductSalesTracker tracker2;

    @BeforeEach
    public void setUp() {
    	tracker1 = new ProductSalesTracker(10);
    	tracker2 = new ProductSalesTracker(15);
     }

    @AfterEach
    public void tearDown() {
    	tracker1 = null;
    	tracker2 = null; // i'm not sure if you want this, as java has garbage collection
     }

    @Test
    public void testAddSale() {
    	assertTrue(tracker1.addSale(69.30), "Should Return True if it succesfully appended");
    	assertTrue(tracker2.addSale(39394141.86), "Should Return True if it succesfully appended with large number");
    	tracker1.addSale(8);
    	assertEquals(77.3, tracker1.totalSales(), "Total sales should be 77.3");
    	assertEquals(8, tracker1.lowestSale(), "Lowest Sale is 8 (checking if it saves old sales)");
     }

    @Test
    public void testTotalSales() {
    	tracker1.addSale(69.30);
    	tracker1.addSale(7);
    	tracker1.addSale(8);
    	assertEquals(84.3, tracker1.totalSales(), "Total sales should be 84.3");
    }

    @Test
    public void testLowestSale() {
    	tracker1.addSale(46.59);
        tracker1.addSale(75.403);
        tracker1.addSale(3.9);

        // Check the lowest sale
        assertEquals(3.9, tracker1.lowestSale(), "Lowest sale should be 3.9");
    }

    @Test
    public void testFinalSalesTotal() {
    	tracker1.addSale(69.30);
    	tracker2.addSale(39394141.86);
    	assertEquals(0, tracker1.finalSalesTotal(), "Should return 0 (less than 2 sales)");
    	tracker1.addSale(7);
    	tracker1.addSale(8);
    	tracker1.addSale(1);
    	assertEquals(84.3, tracker1.finalSalesTotal(), "Total sales should be 84.3");
    }

    @Test
    public void testToString() {
    	tracker1.addSale(69.0);
        tracker1.addSale(420.0);
        
        String expectedString = "69.0 420.0";
        assertEquals(expectedString, tracker1.toString(), "toString should match expected output");
    }
}
