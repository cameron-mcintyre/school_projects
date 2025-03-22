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
        if (list.size() < 2) return;
        int mid = list.size() / 2;
        ArrayList<Integer> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(list.subList(mid, list.size()));
        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    private static void merge(ArrayList<Integer> list, ArrayList<Integer> left, ArrayList<Integer> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
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