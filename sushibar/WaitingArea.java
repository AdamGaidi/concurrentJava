package sushibar;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {
	
	private Queue<Customer> customerQueue = new LinkedList<Customer>();
	private int size;

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        this.size = size;
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized boolean enter(Customer customer) {
    	if (!this.isFull()) {    		
    		this.customerQueue.add(customer);
    		SushiBar.write(Thread.currentThread().getName() + ": Customer" + "Customer" + customer.getCustomerID()+ " is now waiting");
    		this.notifyAll();
    		return true;
    	} else {
    		try {
    			this.wait();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		return false;    		
    	}
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
    	if (!this.isEmpty()) {
    		this.notifyAll();
    		return this.customerQueue.remove();
    	} else {
    		try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		return null;
    	}
    	
    }
    
    public synchronized boolean isEmpty() {
    	if (this.customerQueue.isEmpty()) {
    		return true;
    	}
    	return false;
    }
    
    public synchronized boolean isFull() {
    	if (this.getNumberOfCustomersWaiting() >= this.size) {
    		return true;
    	}
    	return false;
    }
    
    private synchronized int getNumberOfCustomersWaiting() {
    	return this.customerQueue.size();
    }

    // Add more methods as you see fit
}
