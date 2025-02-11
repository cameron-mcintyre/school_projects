package CSC372_projects.CTA5;

import java.util.ArrayList;

//This method recursively multiplies all numbers in the array numbersList (passed from the main GUI).  The method accepts and ArrayList and an index value and returns a long number.

public class Recursion_CTA5_recursiveMethod {
    
    public static Long recursiveMethod(ArrayList<Long> numbersList, Integer index) {

        if (numbersList.size() == index) {  //This will return 1 when the last element is reached in the array.
            return Long.valueOf(1);
        } else {
            return numbersList.get(index) * recursiveMethod(numbersList, index + 1);  //In an array with five elements, this method will be recursively called five times.
        }
    }
}
