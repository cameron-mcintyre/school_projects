public class CSC320_Module4_discussion_whileloop {
    
    public static void main(String[] args){

        int[] numbersList = {1, 4, 5, 23, 543, 1234, 435663, 23564};
        int control = numbersList.length;

        while (control > 0){

            System.out.println(numbersList[numbersList.length - control]);
            control = control - 1;

        }
    }
}
