package sushibar;
public class SynchronizedInteger {
    private int c ;

    public SynchronizedInteger(int initial){
    	c=initial;
    }
    public synchronized void add(int value){
    	c+=value;
    }

    public synchronized void subtract(int value){
    	c-=value;
    }
    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int get() {
        return c;
    }

    public String toString() {
    	return Integer.toString(this.c);
    }
}
