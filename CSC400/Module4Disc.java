package CSC400;
import java.util.Stack;
import java.util.Scanner;

public class Module4Disc {
    public static void main(String[] args){
        Stack<String> stack = new Stack<String>();
        Scanner scnr = new Scanner(System.in);
        int choice;
        String round;

        while(true){

            System.out.println("1: Fire\n2: Reload\n3: Exit");
            choice = scnr.nextInt();

            if(choice == 1){
                if(stack.isEmpty()){
                    System.out.println("Need to reload");
                } else {
                    int size = stack.size();
                    for(int i = 0; i < size; i++){
                        System.out.println("Number of rounds remaining: " + stack.size());
                        String fired = stack.pop();
                        System.out.println(fired + " fired");
                    }
                }
            } else if(choice == 2){
                System.out.println("Enter the name of the round");
                round = scnr.next();
                stack.push(round);
            } else if(choice == 3){
                break;
            }
        }
        scnr.close();
    }
}