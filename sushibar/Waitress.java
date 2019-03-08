package sushibar;
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
        	if (this.waitingArea.isEmpty()) {
        		try {
					this.wait(); //TODO Sjekk om dette er riktig m�te � bruke wait p�.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	Customer currentCustomer = this.waitingArea.next();
        	this.notifyAll(); //Gi beskjed om at en kunde har blitt tatt ut til d�ren. Sjekk om det gj�res s�nn her
        	currentCustomer.order(); //TODO M� gj�re noe med order-greiene her
        	//TODO f�r noe statistikk her
        }
    }


}

