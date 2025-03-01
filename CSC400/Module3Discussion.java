package CSC400;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Module3Discussion {

    public static void main(String[] args){

        final long startTime = System.currentTimeMillis();
    
        Random rand = new Random();
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        int amount = 100000;

        for (int i = 0; i < amount; i++) {
            numberList.add(rand.nextInt(1000));
        }

        //bubbleSort(numberList);
        quickSort(numberList, 0, numberList.size()-1);

        System.out.println("Middle number: " + numberList.get(numberList.size()/2));
        System.out.println("Size of list: " + numberList.size());


        final long endTime = System.currentTimeMillis();
    
        long elapsedTime = endTime - startTime;
    
        System.out.println("Elapsed time: " + elapsedTime);
    }

    public static void bubbleSort(ArrayList<Integer> numberList) {
        for (int i = 0; i < numberList.size() -1; i++) {
            int indexSmallest = i;
            for (int j = i + 1; j < numberList.size(); j++) {
                if (numberList.get(j) < numberList.get(indexSmallest)) {
                    indexSmallest = j;
                }
            }
            int temp = numberList.get(i);
            numberList.set(i, numberList.get(indexSmallest));
            numberList.set(indexSmallest, temp);
        }
    }

    public static void quickSort(ArrayList<Integer> numberList, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(numberList, low, high);
        quickSort(numberList, low, pivotIndex);
        quickSort(numberList, pivotIndex + 1, high);
    }
    
    private static int partition(ArrayList<Integer> numberList, int low, int high) {
        int midpoint = low + (high - low) / 2;
        int pivot = numberList.get(midpoint);
    
        boolean done = false;
        while (!done) {
            while (numberList.get(low) < pivot) {
                low++;
            }
            while (numberList.get(high) > pivot) {
                high--;
            }
    
            if (low >= high) {
                done = true;
            } else {

                Collections.swap(numberList, low, high);
                low++;
                high--;
            }
        }
        return high;
    }
}