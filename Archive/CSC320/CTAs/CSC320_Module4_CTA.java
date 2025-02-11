package CTAs;
import java.util.Scanner;

public class CSC320_Module4_CTA {
    public static void main(String[] args){
        
        //A program to read a set of five floats using a while loop and output some simple maths.
        
        Scanner scnr = new Scanner(System.in);
        float[] userNums = new float[5];
        float value;
        int count = 0;
        float sum = 0;
        float avg = 0;
        float max = 0;
        float min = 0;
        
        //Number ingest section

        while (count < 5){
            System.out.println("Please enter an number: ");
            value = scnr.nextFloat();
            userNums[count] = value;
            count = count + 1;    
        }

        //Computation section
        count = 0;
        min = userNums[count];
        while (count < 5) {

            sum = sum + userNums[count];

            if (userNums[count] > max){
                max = userNums[count];
            }
            
            if (userNums[count] < min){
                min = userNums[count];
            }

            
            count = count + 1;
            }

        avg = sum / userNums.length;
        
        //Output section
        System.out.println("The total is: " + sum);
        System.out.println("The average is: " + avg);
        System.out.println("The maximum is: " + max);
        System.out.println("The minimum is " + min);
        System.out.println("The 20% interest added for " + sum + " results in a total of " + sum * 1.2);

        scnr.close();
    } 
}
