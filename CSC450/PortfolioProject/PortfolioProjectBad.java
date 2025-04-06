package CSC450.PortfolioProject;
import java.lang.Thread;

public class PortfolioProjectBad extends Thread {
    
    //create class variables
    public static       int         counter = 0;            //the counter
    private             boolean     upOrDown;               //var to indicate count up or count down

    //constructor - will help us choose countup or countdown
    public PortfolioProjectBad(boolean goingUp){
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
        //basic count up
        for(int i = 0; i < 20; i++){
            counter++;
            System.out.println(Thread.currentThread() + ": Up: " + counter);
        } 
    }

    public void counter2(){
        //basic count down
        for(int i = 0; i < 20; i++){
            counter--;
            System.out.println(Thread.currentThread() + ": Dn: " + counter);
        }
    }


    public static void main(String[] args) throws InterruptedException{

        //build our two threads for counting.  false = up, true = down
        PortfolioProjectBad thread1 = new PortfolioProjectBad(false);
        PortfolioProjectBad thread2 = new PortfolioProjectBad(true);
        
        //name threads
        thread1.setName("CountUpThread");
        thread2.setName("CountDownThread");

        //start the threads
        thread1.start();
        thread2.start();
        
        //block main thread until the counters are completed
        thread1.join();
        thread2.join();

        //main method can complete now that thread1 and thread2 are finished
        System.out.println("Threads complete.");
    } 
}
