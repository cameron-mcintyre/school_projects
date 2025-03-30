package CSC400.Module5CTA;
import java.util.ArrayList;

public class RadixSortTest {
    public static void main(String[] args){

        RadixSort testSort = new RadixSort();

        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(56);
        nums.add(-12);
        nums.add(24);
        nums.add(100);
        nums.add(0);
        nums.add(-1);
        nums.add(3);
        nums.add(8);
        nums.add(-1);

        testSort.setup(nums);
    }
}