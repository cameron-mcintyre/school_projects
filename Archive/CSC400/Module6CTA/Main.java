package CSC400.Module6CTA;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        //creating new list
        CustomLinkedList linkedList = new CustomLinkedList();
        Scanner scnr = new Scanner(System.in);
        int nums = 0;

        //gets user input to see how many rand nums to add
        System.out.println("Enter the number of integers to add to the file: ");
        try{
            nums = scnr.nextInt();
        } catch(InputMismatchException e){
            System.out.println("Please enter an integer as an input.");
        }

        //add random nums to list
        writeRandsToFile(nums);  //write to file
        readRandsIntoFile(linkedList, nums); //read from file into list
        printOutList(linkedList);  //print list
        
        //delete operation
        System.out.println("Enter the value you wish to delete: ");
        int deleteMe = scnr.nextInt();
        linkedList.delete(deleteMe);  //call delete method
        printOutList(linkedList);  //print out list again

        scnr.close();
    }

    //write a number of random ints into a file
    public static void writeRandsToFile(int nums) throws IOException{

        Random rand = new Random();
        FileWriter fout = new FileWriter("integers.txt");

        //writes a user input amount of rands into a file
        while(nums > 0){
            int randomNum = rand.nextInt(99);
            String randomString = String.valueOf(randomNum);
            fout.write(randomString + "\n");
            nums--;
        }
        
        fout.close();
    }

    //this reads the file
    public static void readRandsIntoFile(CustomLinkedList linkedList, int nums) throws FileNotFoundException {
        
        File randFile = new File("integers.txt");
        Scanner fread = new Scanner(randFile);

        //reads nums from the file
        while(nums > 0){
            try{
                int temp = Integer.parseInt(fread.nextLine());
                linkedList.insert(temp);
                nums--;
            } catch (NumberFormatException e){
                System.out.println("Invalid integer input in file!");
            } 
        }
        fread.close();
    }

    //prints out the items in the list and the list size
    public static void printOutList(CustomLinkedList linkedList){
        
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\nList size: " + linkedList.size());
    }
}