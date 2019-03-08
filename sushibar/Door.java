package sushibar;
/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {
	
	private WaitingArea waitingArea;
    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the waiting area
     */
    @Override
    public void run() {
        while (SushiBar.isOpen) {
        	//TODO Try adding new customer to waitingArea at given intervalls. Make sure it's synchronized
        	//TODO Add the random intervalls
        	if (!this.waitingArea.isFull()) {
        		this.waitingArea.enter(new Customer());
        		this.notifyAll(); //Varsle servitører om at ny kunde har kommet inn i tilfelle noen står og venter
        	} else {
        		try {
					this.wait(); //TODO Riktig bruk av wait? Bekymret for at den kanskje 'waiter' Door istedenfor Door sin tilgang på waitingArea
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    }

    // Add more methods as you see fit
}
