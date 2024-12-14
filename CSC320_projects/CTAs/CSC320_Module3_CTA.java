package CTAs;
import java.util.Scanner;

public class CSC320_Module3_CTA {
    
    public static void main(String[] args){
        
        Scanner getinfo = new Scanner(System.in);
        double taxes = 0;        
        double income = 0;
        boolean keepgoing = true;
        String keepgoingchoice;

        while (keepgoing == true) {

        System.out.println("Welcome of the tax calculator! ");
        System.out.println("Please enter your weekly income:");
        income = getinfo.nextDouble();
        
            if (income < 0){
                System.out.println("You have no taxes! Taxes = " + taxes);
            } else if (income < 500.00){
                taxes = income * 0.1;
                System.out.println("You are taxed at 10%.  Taxes = " + taxes);
            } else if (income < 1500.00){
                taxes = income * 0.15;
                System.out.println("You are taxed at 15%.  Taxes = " + taxes); 
            } else if (income < 2500){
                taxes = income * 0.2;
                System.out.println("You are taxed at 20%.  Taxes = " + taxes);
            } else {
                taxes = income * 0.3;
                System.out.println("You are taxed at 30%.  Taxes = " + taxes);
            }

            System.out.println("Do you want to go again? Enter 'N' to stop: ");
            keepgoingchoice = getinfo.next();
            if (keepgoingchoice.equals("N")){
                keepgoing = false;
            }
        }

        getinfo.close();
    }
}
