import java.util.Scanner;

public class CSC320_Module5_disc {

    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        String[] trainCars = new String[10];
        int i;

        System.out.println("Your train has one locomotive and ten cars!  Enter your locomotive: ");
        trainCars[0] = scnr.next();

        for (i = 1; i < trainCars.length; ++i){
            System.out.println("Enter the type of train car");
            trainCars[i] = scnr.next();
            System.out.println((trainCars.length - i - 1) + " cars left!");
        }

        System.out.println("");
        System.out.println("Your train has the following cars:");

        for (i = 0; i < trainCars.length; ++i){
            System.out.print(trainCars[i] + "-");
            
        }

        System.out.print("Caboose");
    }
    
}