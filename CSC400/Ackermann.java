package CSC400;
import java.util.Stack;
import javafx.util.Pair;

public class Ackermann {
    public static void main(String[] args){

        Stack<Pair<Integer, Integer>> ackStack = new Stack<>();
        int x = 3;
        int y = 5;
        int count = 0;

        ackStack.push(new Pair<>(x, y));

        while(!ackStack.isEmpty()){
            count++;
            Pair<Integer, Integer> nums = ackStack.pop();
            int i = nums.getKey();
            int j = nums.getValue();
            
            if(i == 0){
                System.out.println(j + 1);
            } else if (i > 0 && j == 0){
                ackStack.push(new Pair<>(i - 1, 1));
            } else if (i > 0 && j > 0){
                Pair<Integer, Integer> newPair = new Pair<>(i, j - 1);
                ackStack.push(newPair);
            }
        }
        System.out.println("Recursions: " + count);
    }
}