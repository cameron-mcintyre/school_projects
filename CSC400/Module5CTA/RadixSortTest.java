package CSC400.Module5CTA;

public class RadixSortTest {
    public static void main(String[] args){

        RadixSort testSort = new RadixSort();

        int[] nums = {783, 99, 472, 182, 264, 543, 356, 295, 692, 491, 94};  //required nums
        int[] nums1 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //test case
        int[] nums2 = {1, 11, 111, 1111, 2, 22, 222, 2222, 3, 33, 333, 3333}; //test case
        testSort.setup(nums);
        testSort.setup(nums1);
        testSort.setup(nums2);
    }
}