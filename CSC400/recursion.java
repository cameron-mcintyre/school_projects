package CSC400;

public class recursion {
    
    public static void main(String[] args){
        long startTime = System.nanoTime();
        int nums = 3;
        int result = 0;

        //for(int i = 0; i < nums + 1; i++){
        //    result = result + i;
        //}

        result = summation2(5, 5);
        //result = summation1(4);
        long stopTime = System.nanoTime();
        //System.out.println("Num: " + nums);
        System.out.println("Result: " + result);
        System.out.println("Time taken:" + (stopTime - startTime));
    }

    public static int summation1(int num){
        if(num == 0){
            return 1;
        } else if (num == 1) {
            return num;
        } else {
            System.out.println(num);
            return num + summation1(num - 1);
        }
    }

    public static int summation2(int num1, int num2){
        if(num2 == 0){
            return 1;
        } else if (num2 == 1) {
            return num1;
        } else {
            System.out.println(num1 + " " + num2);
            return num1 + summation2(num1, num2 - 1);
        }
    }
}