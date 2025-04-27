package CSC400;
import java.util.Random;
import java.util.ArrayList;

public class sortingTesting {
    
    public static void main(String[] args){
        Random rand = new Random();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int numOfRuns = 1000;
        int sizeOfList = 500;
        System.out.println("NumRuns: " + numOfRuns + " - ArraySize: " + sizeOfList);

        long startTime1 = System.nanoTime();
        for(int j = 0; j < numOfRuns; j++){
            for(int i = 0; i < sizeOfList; i++){
                int temp = rand.nextInt(100);
                nums.add(temp);
            }
            mergeSort(nums);
            //System.out.println("Nums: " + nums.get(0) + ", " + nums.get(nums.size()/2) + ", " + nums.get(nums.size()-1));
        }
        long endTime1 = System.nanoTime();
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Time Merge: " + elapsedTime1/1000);
        nums.clear();

        long startTime2 = System.nanoTime();
        for(int j = 0; j < numOfRuns; j++){
            for(int i = 0; i < sizeOfList; i++){
                int temp = rand.nextInt(100);
                nums.add(temp);
            }
            insertionSort(nums);
            //System.out.println("Nums: " + nums.get(0) + ", " + nums.get(nums.size()/2) + ", " + nums.get(nums.size()-1));
        }
        long endTime2 = System.nanoTime();
        long elapsedTime2 = endTime2 - startTime2;
        System.out.println("Time Insertion: " + elapsedTime2/1000);
    }

    public static void mergeSort(ArrayList<Integer> list) {
        if (list.size() < 2) {
            return; //list of one item.  this is um already sorted
        }
        //divide list in the middle
        int mid = list.size() / 2;

        //split into smaller lists
        ArrayList<Integer> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(list.subList(mid, list.size()));
        
        //get recursive on the left and right arraylists
        //this will keep recursively iterating until the size of each array is one object (<2)
        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    private static void merge(ArrayList<Integer> list, ArrayList<Integer> left, ArrayList<Integer> right) {
        int i = 0;
        int j = 0;
        int k = 0;

        //this part is basically just comparing the left to the right subarrays
        while (i < left.size() && j < right.size()) {

            //compare left side to right
            if (left.get(i) <= right.get(j)) {  //if left is smaller
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));  //if left is bigger
            }
        }

        //now we have to copy the elements back into the array, starting with i
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        //now we do j
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

    public static void insertionSort(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}