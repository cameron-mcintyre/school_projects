package CSC450.PortfolioProject;
import java.lang.Thread;

public class PortfolioProject extends Thread {
    
    //create class variables
    public static       int         counter = 0;            //the counter
    private             boolean     upOrDown;               //var to indicate count up or count down
    private static      Object      mut = new Object();     //mutex

    //constructor - will help us choose countup or countdown
    public PortfolioProject(boolean goingUp){
        this.upOrDown = goingUp;
    }

    //override run() to decide if thread is counting up or down
    @Override
    public void run(){
        //decide which count method to use
        if(upOrDown == false){
            counter1();
        } else {
            counter2();
        }
    }

    //counting up
    public void counter1(){
        //lock the mutex
        synchronized(mut){  
            //basic count up
            for(int i = 0; i < 20; i++){
                counter++;
                System.out.println(Thread.currentThread() + ": Up: " + counter);
            }
            //unlock mutex and notify other threads
            mut.notifyAll(); 
        }
    }

    public void counter2(){
        //lock the mutex
        synchronized(mut){ 
            //basic count down
            for(int i = 0; i < 20; i++){
                counter--;
                System.out.println(Thread.currentThread() + ": Dn: " + counter);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{

        long starttime = System.currentTimeMillis();

        //build our two threads for counting.  false = up, true = down
        PortfolioProject thread1 = new PortfolioProject(false);
        PortfolioProject thread2 = new PortfolioProject(true);
        
        //name threads
        thread1.setName("CountUpThread");
        thread2.setName("CountDownThread");

        //start the threads
        thread1.start();
        thread2.start();
        
        //block main thread until the counters are completed
        thread1.join();
        thread2.join();
        
        //keep thread2 waiting until thread1 is complete
        while(thread1.isAlive()){
            thread2.wait();
        }

        //main method can complete now that thread1 and thread2 are finished
        System.out.println("Threads complete.");

        long stoptime = System.currentTimeMillis();
        System.out.println("Time: " + (stoptime - starttime));
    } 
}