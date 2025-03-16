package CSC400;

public class RadixSort {
    
    public static void main(String[] args){
        int[] nums = {783, 99, 472, 182, 264, 543, 356, 295, 692, 491, 94};  //required nums
        //int[] nums = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //test case
        //int[] nums = {1, 11, 111, 1111, 2, 22, 222, 2222, 3, 33, 333, 3333}; //test case
        setup(nums);
        
    }

    public static void setup(int[] nums){
        int[] sorted = new int[nums.length];
        int max = 0;
        int arrayLen = nums.length;

        //find max
        for(int i = 0; i < arrayLen; i++){
            if(nums[i] > max){
                max = nums[i];
            }
        }

        //find number of sorts we need to do (number of digits)
        int exp = 1;
        while(max / exp >= 1){
            //call the function to sort everything for the digit we're sorting right now.
            //this produces an array "sorted" that is sorted according to the digit, but not entirely sorted.
            sorted = radixSort(arrayLen, nums, exp);

            //copy the sorted digit array into nums
            for(int i = 0; i < arrayLen; i++){
                nums[i] = sorted[i];
            }

            //move to the next digit (ones, tens, hundreds, thousands, etc)
            exp = exp * 10;
        }

        //print out the array in order
        for(int i = 0; i < arrayLen; i++){
            System.out.println(nums[i]);
        }
    }

    //I got help online for this part (thanks geeksforgeeks, yet again)
    //https://www.geeksforgeeks.org/radix-sort/
    //this part feels like deep magic to me.  I kind of get what we're doing here,
    //but it feels like I only have a glimpse.  I'll try to comment through.
    public static int[] radixSort(int arrayLen, int[] nums, int exp){
        //setup a holding array
        int[] output = new int[arrayLen];
        int i;
        int count[] = new int[10];
        
        //this for loop calculates the number of numbers in nums[] with a digit for that i.
        //these are stored in count[i], which has a sort of watermark of the number of times
        //a number has that digit.
        for(i = 0; i < arrayLen; i++){
            count[(nums[i] / exp) % 10]++;
        }

        //this is where it gets confusing.  I had to watch a youtube video to figure out what 
        //is going on in this part: https://youtu.be/mVRHvZF8xtg?si=aNzUMENRAL3IvMM6
        //this part can be visualized at 5:58 in the video, it's keeping track of where each number
        //in the initial array should be positioned according to it's "bucket".
        //I'm still not totally sure I get it.
        for(i = 1; i < 10; i++){
            count[i] = count[i] + count[i - 1];
        }

        //here we're using our properly arranged count[] array to tell us where each item in nums[]
        //is going to get placed into our output array.
        //count[] has to get dropped for each number we put into the output array to accomodate
        //multiples of each digit.
        for(i = arrayLen - 1; i >= 0; i--){
            output[count[(nums[i] / exp) % 10] - 1] = nums[i];
            count[(nums[i] / exp) % 10]--;
        }

        return output;
    }
}