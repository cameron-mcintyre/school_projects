import java.util.Scanner;

public class gitTestProgram {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a value: ");

        String userInput = scanner.nextline();

        System.out.println("You entered: " + userInput);

        scanner.close();
    }
}