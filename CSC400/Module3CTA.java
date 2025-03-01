package CSC400;
import java.util.Random;
import java.util.ArrayList;

public class Module3CTA {
    
    public static void main(String[] args) {

        Random generator = new Random();
        ArrayList<Integer> myNums = new ArrayList<Integer>();

        //build the arraylist of random ints
        for(int i = 0; i < 6; i++){
            int newNum = generator.nextInt(6);
            newNum = newNum + 1;
            myNums.add(newNum);
        }
        System.out.println(myNums);

        //iterate through the list to find a number
        for(int j = 1; j < (myNums.size() + 1); j++){
            if(myNums.contains(j)){
                continue;
            } else {
                System.out.println(j + " is not on the list!");
            }
        }
        System.out.println("Program complete");
    }
}