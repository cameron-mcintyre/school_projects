package CSC400.Module5CTA;
import java.util.ArrayList;
import java.util.Collections;

public class RadixSort {

    public void setup(ArrayList<Integer> nums){
        ArrayList<Integer> sortedPos = new ArrayList<Integer>();
        ArrayList<Integer> sortedNeg = new ArrayList<Integer>();
        int maxNeg = 0;
        int maxPos = 0;
        ArrayList<Integer> numsPos = new ArrayList<Integer>();
        ArrayList<Integer> numsNeg = new ArrayList<Integer>();

        //separate out the positive from negative numbers in arraylists
        for(int i = 0; i < nums.size(); i++){
            if(nums.get(i) > 0){
                numsPos.add(nums.get(i));
            } else {
                numsNeg.add(Math.abs(nums.get(i)));
            }
        }

        //find the local max for numsNeg
        for(int i = 0; i < numsNeg.size(); i++){
            if(maxNeg < numsNeg.get(i)){
                maxNeg = numsNeg.get(i);
            }
        }

        //find the local max for numsPos
        for(int i = 0; i < numsPos.size(); i++){
            if(maxPos < numsPos.get(i)){
                maxPos = numsPos.get(i);
            }
        }

        //this does the sorting based on the digit, starting with the negative numbers
        int exp = 1;
        while(maxNeg / exp >= 1){
            //call the function to sort everything for the digit we're sorting right now.
            //this produces an array "sorted" that is sorted according to the digit, but not entirely sorted.
            sortedNeg = radixSort(numsNeg.size(), numsNeg, exp);
            numsNeg = sortedNeg;
            //move to the next digit (ones, tens, hundreds, thousands, etc)
            exp = exp * 10;
        }
        Collections.reverse(sortedNeg); //we have to reverse the negative numbers
        //copy the sorted digit array into nums
        for(int i = 0; i < sortedNeg.size(); i++){
            sortedNeg.set(i, -(sortedNeg.get(i)));
        }

        //now we do the positive numbers
        exp = 1;
        while(maxPos / exp >= 1){
            //call the function to sort everything for the digit we're sorting right now.
            //this produces an array "sorted" that is sorted according to the digit, but not entirely sorted.
            sortedPos = radixSort(numsPos.size(), numsPos, exp);
            numsPos = sortedPos;

            //move to the next digit (ones, tens, hundreds, thousands, etc)
            exp = exp * 10;
        }

        //clear the main list, and add in the negs, then pos
        nums.clear();
        nums.addAll(numsNeg);
        nums.addAll(numsPos);

        //print out the array in order
        for(int i = 0; i < nums.size(); i++){
            System.out.println(nums.get(i));
        }
    }


    //divides the array into buckets according to the number of that digit
    private static ArrayList<Integer> radixSort(int arrayLen, ArrayList<Integer> nums, int exp){
        //setup a holding array
        ArrayList<Integer> output = new ArrayList<Integer>();
        int i;
        int count[] = new int[10];
        
        //this for loop calculates the number of numbers in nums[] with a digit for that i.
        //these are stored in count[i], which has a sort of watermark of the number of times
        //a number has that digit.
        for(i = 0; i < arrayLen; i++){
            count[(nums.get(i) / exp) % 10]++;
        }

        //this is where it gets confusing.  I had to watch a youtube video to figure out what 
        //is going on in this part: https://youtu.be/mVRHvZF8xtg?si=aNzUMENRAL3IvMM6
        //this part can be visualized at 5:58 in the video, it's keeping track of where each number
        //in the initial array should be positioned according to it's "bucket".
        //I'm still not totally sure I get it.
        for(i = 1; i < 10; i++){
            count[i] = count[i] + count[i - 1];
        }

        for(int j = 0; j < arrayLen; j++){
            output.add(0);
        }

        //here we're using our properly arranged count[] array to tell us where each item in nums[]
        //is going to get placed into our output array.
        //count[] has to get dropped for each number we put into the output array to accomodate
        //multiples of each digit.
        for(i = arrayLen - 1; i >= 0; i--){
            output.set(count[(nums.get(i) / exp) % 10] - 1, nums.get(i));
            count[(nums.get(i) / exp) % 10]--;
        }

        return output;
    }
}