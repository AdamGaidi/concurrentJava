package sushibar;
import sushibar.SynchronizedInteger;
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
