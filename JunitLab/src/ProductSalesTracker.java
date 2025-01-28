import java.util.Arrays;

public class ProductSalesTracker {
    private double[] sales;
    private int salesSize;

    public ProductSalesTracker(int capacity) {
        sales = new double[capacity];
        Arrays.fill(sales, -1);
        salesSize = 0;
    }

    public boolean addSale(double sale) {
    	int freeIndex = -1;

    	for (int i = 0; i < sales.length; i++) {
    	    if (sales[i] == -1) {
    	    	freeIndex = i;
    	        break;
    	    }
    	}
    	
    	if (freeIndex == -1)
    		return false;
    	
    	sales[freeIndex] = sale;
    	return true;
    }

    public double totalSales() {
    	double tot = 0.0;
    	for (double i : sales) {
    	  if (i != -1)
    		  tot += i;
    	}
    	return tot;
    }

    public double lowestSale() {
    	double lowest = sales[0];
    	if (lowest == -1) {
    		return 0;
    	}
    	for (int i = 0; i < sales.length; i++) {
    	    if (sales[i] == -1) {
    	        break;
    	    }
    	    
    	    if (lowest > sales[i]) {
    	    	lowest = sales[i];
    	    }
    	}
    	return lowest;
    }

    public double finalSalesTotal() {
    	int freeIndex = -1;

    	for (int i = 0; i < sales.length; i++) {
    	    if (sales[i] == -1) {
    	    	freeIndex = i;
    	        break;
    	    }
    	}
    	if (freeIndex < 1) {
    		return 0;
    	}
    	return totalSales() - lowestSale();
    }

    public String toString() {
    	String output = "";
    	for (double a : sales) {
    		if (a == -1)
    			continue;
            output += a + " ";
        }
    	String strippedString = output.strip();
    	return strippedString;
    }
}

