package sushibar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import sushibar.Waitress;

import java.util.ArrayList;
import java.util.List;

public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 15;
    private static int waitressCount = 8;
    private static int duration = 4;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 200; // Used to calculate the time the customer spends eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        // TODO initialize the bar and start the different threads
        Clock clock = new Clock(duration);
        WaitingArea waitingArea = new WaitingArea(SushiBar.waitingAreaCapacity);
        List<Thread> threadList = new ArrayList<Thread>(); 
        for (int i = 0; i <= waitressCount; i++){
        	threadList.add(new Thread(new Waitress(waitingArea)));
        }
        threadList.add(new Thread(new Door(waitingArea)));
        
        for (Thread thread: threadList) {
        	thread.start();
        }
        
        for (Thread thread: threadList) {
        	try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        SushiBar.write("***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
        SushiBar.write("STATISTICS:" + "\n" + "Customer Counter: " + SushiBar.customerCounter + "\n" + 
        "Total Orders: " + SushiBar.totalOrders + "\n" + "Served Orders: " + SushiBar.servedOrders + "\n" + "Takeaway Orders: " + SushiBar.takeawayOrders);
    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
