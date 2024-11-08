import java.util.Scanner;

public class CSC320_Module5_disc {

    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        String[] trainCars = new String[10];
        int i;
        //Build the locomotive.
        System.out.println("Your train has one locomotive and ten cars!  Enter your locomotive: ");
        trainCars[0] = scnr.next();
        //Now add all the cars.
        for (i = 1; i < trainCars.length; ++i){
            System.out.println("Enter the type of train car");
            trainCars[i] = scnr.next();
            System.out.println((trainCars.length - i - 1) + " cars left!");
        }
        //Print out the train.
        System.out.println("");
        System.out.println("Your train has the following cars:");

        for (i = 0; i < trainCars.length; ++i){
            System.out.print(trainCars[i] + "-");
            
        }
        //Don't forget a caboose!  I know trains don't use them anymore, I don't care.
        System.out.print("Caboose");
    }
    
}
