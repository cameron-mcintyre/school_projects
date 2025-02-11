package CTAs;
import java.util.Scanner;
import java.util.ArrayList;

public class CSC320_Module5_CTA {
    
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);

        ArrayList<String> daysArray = new ArrayList<String>();
        ArrayList<Double> avgTemps = new ArrayList<Double>();
        int i;
        double counter = 0;
        String userPick;
        int userPickVal = 7;
        int goAgain = 1;

        avgTemps.add(71.9);
        avgTemps.add(73.8);
        avgTemps.add(75.1);
        avgTemps.add(68.3);
        avgTemps.add(64.8);
        avgTemps.add(66.7);
        avgTemps.add(72.4);

        daysArray.add("Sunday");
        daysArray.add("Monday");
        daysArray.add("Tuesday");
        daysArray.add("Wednesday");
        daysArray.add("Thursday");
        daysArray.add("Friday");
        daysArray.add("Saturday");
        daysArray.add("Weekly");

        while(goAgain != 0){

            System.out.println("This is the temperature program.  Please enter one of the following choices: ");
            
            for (i = 0; i < daysArray.size(); ++i){
                System.out.println(daysArray.get(i));
            }
            System.out.println("--------------------------------");
            System.out.println("");

            userPick = scnr.next();

            for (i = 0; i < daysArray.size(); ++i){
                if (userPick.equals(daysArray.get(i))){
                    userPickVal = i;
                    break;
                }
            }
                
            if (userPickVal < 7){
                System.out.println("On " + daysArray.get(userPickVal) + ", the average temperature will be " + avgTemps.get(userPickVal));
            }
            else{
                for (i = 0; i < avgTemps.size(); ++i){
                    counter = counter + avgTemps.get(i);
                }
                counter = counter / avgTemps.size();
                System.out.println("The weekly average is " + counter + ".");
            } 
        
            System.out.println("Do you want to go again?  Enter 0 to end, or 1 to continue: ");
            goAgain = scnr.nextInt();
        }
        scnr.close();
    }
}
