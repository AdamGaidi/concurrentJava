package sushibar;
import sushibar.SynchronizedInteger;
import java.lang.Math;
import sushibar.SushiBar;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {

	private static SynchronizedInteger uniqueIDGenerator = new SynchronizedInteger(0);
	private final int uniqueID;
	
    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        this.uniqueID = Customer.uniqueIDGenerator.get();
        Customer.uniqueIDGenerator.increment();
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
    	int takeAwayOrders = (int)(Math.random() * SushiBar.maxOrder);
    	int servedOrders = SushiBar.maxOrder - takeAwayOrders;
    	
    	try {
    		SushiBar.write(Thread.currentThread().getName() + ": Customer" + "Customer" + this.getCustomerID()+ " is now eating");
			Thread.sleep(SushiBar.customerWait * servedOrders);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	SushiBar.customerCounter.increment();
    	SushiBar.servedOrders.add(servedOrders);
    	SushiBar.takeawayOrders.add(takeAwayOrders);
    	SushiBar.totalOrders.add(servedOrders + takeAwayOrders); 
    	
    	SushiBar.write("Customer" + this.getCustomerID()+ " is now leaving");
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        return this.uniqueID;
    }

    // Add more methods as you see fit
}
