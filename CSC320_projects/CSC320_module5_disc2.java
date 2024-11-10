import java.util.Scanner;

public class CSC320_module5_disc2 {    
    public static void main(String[] args){
        int i;
        Scanner scnr = new Scanner(System.in);

        String [][] studentMeals = new String [2][4];

        for (i = 0; i < 4; ++i){
            System.out.println("Enter the student and their meal separated by a space: ");
            studentMeals[0][i] = scnr.next();
            studentMeals[1][i] = scnr.next();
        }

        for (i = 0; i < 4; ++i){
            System.out.print(studentMeals[0][i] + " ");
            System.out.println(studentMeals[1][i]);
        }

        scnr.close();
    }
}


