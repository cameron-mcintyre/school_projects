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
        
        //According to the textbook, we might be better off using doubles instead of floats, but the requirements said float, so float it is.

        while (count < 5){
            System.out.println("Please enter an number: ");
            value = scnr.nextFloat();
            userNums[count] = value;
            count = count + 1;    
        }

        //Computing the sum:
        count = 0;
        while (count < 5) {
            sum = sum + userNums[count];
            count = count + 1;
            }
        System.out.println("The sum is: " + sum);

        //Computing the average:
        avg = sum / userNums.length;
        System.out.println("The average is: " + avg);

        //Computing the maximum:
        count = 0;
        while (count < 5) {
            if (userNums[count] > max){
                max = userNums[count];
            }
            count = count + 1;
            }
        System.out.println("The maximum is: " + max);

        //Computing the minimum:
        count = 0;
        min = userNums[count];
        while (count < 5) {
            if (userNums[count] < min){
                min = userNums[count];
            }
            count = count + 1;
            }
        System.out.println("The minimum is: " + min);

        //Computing the interest:
        count = 0;
        while (count < 5) {
            System.out.println("The 20% interest added for " + userNums[count] + " results in a total of " + userNums[count] * 1.2);
            count = count + 1;
            }

        scnr.close();
    }
    
}
