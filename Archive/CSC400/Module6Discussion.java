package CSC400;

import java.util.Random;

public class Module6Discussion {

    public static void main(String[] args){
        
        int nums[] = {4, 9, 3, 2, 1, 3, 7, 7, 8};
        //randomSort(nums);
        quickSort(nums, 0, nums.length - 1);

        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i] + " ");
        }
    }

    public static void quickSort(int nums[], int start, int end){
        //does the pivoting
        if(start < end){
            int middle = pivot(nums, start, end);  //Middle pivot
            quickSort(nums, start, middle - 1);  //recursion, sort bottom
            quickSort(nums, middle + 1, end);  //recursion, sort top
        }
    }

    //turns the array around the pivot
    public static int pivot(int nums[], int start, int end){
        //find the pivot in the middle of the set and get the value in the pivot
        int pivot = start + ((end - start) / 2);
        int pivotnum = nums[pivot];
        int i = (start - 1);

        //put the pivot at the end of the list
        int temp0 = nums[pivot];
        nums[pivot] = nums[end];
        nums[end] = temp0;
        
        //go thru the array and move big things right and small things left
        for(int j = start; j < end; j++){
            if(nums[j] < pivotnum){
                i++;
                int temp1 = nums[i];
                nums[i] = nums[j];
                nums[j] = temp1;
            }
        }

        //put pivot back in the middle.
        int temp2 = nums[i + 1];
        nums[i + 1] = nums[end];
        nums[end] = temp2;
        
        return i + 1;
    }

    public static void randomSort(int nums[]){
        Random rand = new Random();
        int count = 0;
       
        while(true){
            
            //if the values aren't in order
            if(nums[0] < nums[1] && nums[1] < nums[2] && nums[2] < nums[3]){
                System.out.println(nums[0] + " " + nums[1] + " " + nums[2] + " " + nums[3] + " count: " + count);
                break;
            } else {
                //replace all numbers with random other numbers
                for(int i = 0; i < nums.length; i++){
                    nums[i] = rand.nextInt(10);
                }
                count++;
            }
        }
    }
}