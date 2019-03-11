package sushibar;
import sushibar.SushiBar;
/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

	private WaitingArea waitingArea;
    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        while (!this.waitingArea.isEmpty() || SushiBar.isOpen) {
        	Customer currentCustomer = this.waitingArea.next();
        	if (currentCustomer != null) {
        		SushiBar.write(Thread.currentThread().getName() + ": Customer" + currentCustomer.getCustomerID()+ " is now fetched");
       			try {
       				Thread.sleep(SushiBar.waitressWait);
       			} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
       			currentCustomer.order();        		        			
        	}
        	
        }
    }


}
