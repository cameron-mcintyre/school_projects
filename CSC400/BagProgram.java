package CSC400;

import java.util.Scanner;

public class BagProgram {
    
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        Suitcase<Object> newBag = new Suitcase<>();
        int selection;

        System.out.println("~~~~Welcome to the Bag Program!~~~~\n");
        
        while (true) {
            System.out.println("Please make a selection: ");
            System.out.println("1: Add items to bag");
            System.out.println("2: Print contents of the bag");
            System.out.println("3: Search for an item in the bag");
            System.out.println("4: Remove an item from the bag");
            System.out.println("5: Count the amount of an item in the bag");
            System.out.println("6: Exit");
            selection = scnr.nextInt();

            if (selection == 1) {addToBag(newBag, scnr);}
            else if (selection == 2) {printBagContents(newBag);}
            else if (selection == 3) {searchInBag(newBag, scnr);}
            else if (selection == 4) {removeFromBag(newBag, scnr);}
            else if (selection == 5) {countInBag(newBag, scnr);}
            else if (selection == 6) {break;}
        }
        
        scnr.close(); 
        System.exit(0);
    }

    //prints the bag and bag item count
    public static void printBagContents(Suitcase<Object> newBag) {
        System.out.println("\nCurrent bag contents: " + newBag.printBag());
        System.out.println("Current number of things in the bag: " + newBag.countBag() + "\n");
    }

    //adds items to the bag endlessly until a user types N
    public static void addToBag(Suitcase<Object> newBag, Scanner scnr) {
        Object input;
        String choice;

        while (true) {
            System.out.println("Do you want to add something to the bag?  Y/N");
            choice = scnr.next();

            if (choice.equals("Y") || choice.equals("y")){
                System.out.println("Please enter something to put in the bag: ");
                input = scnr.next();
                newBag.add(input);
                continue;
            } else if (choice.equals("N") || choice.equals("n")) {
                break;
            } else {
                System.out.println("Please make a valid selection of Y or N.");
                continue;
            }
        }
    }

    //searches for items in the bag and indicates t/f
    public static void searchInBag(Suitcase<Object> newBag, Scanner scnr) {
        Object choice;

        while (true) {
            System.out.println("Do you want to search for something in the bag?  Y/N");
            choice = scnr.next();

            if (choice.equals("Y") || choice.equals("y")){
                System.out.println("Please enter a thing to search inside the bag for: ");
                choice = scnr.next();
                System.out.println("Your choice is in the bag: " + newBag.contains(choice));
                continue;
            } else if (choice.equals("N") || choice.equals("n")) {
                break;
            } else {
                System.out.println("Please make a valid selection of Y or N.");
                continue;
            }
        }
    }

    //removes object from the bag
    public static void removeFromBag(Suitcase<Object> newBag, Scanner scnr) {
        Object choice;
        int itemIndex;

        while (true) {
            System.out.println("Do you want to remove something from the bag?  Y/N");
            choice = scnr.next();

            if (choice.equals("Y") || choice.equals("y")){
                System.out.println("Please enter an item to remove: ");
                choice = scnr.next();
                itemIndex = newBag.findIndex(choice);
                System.out.println("Item of value " + choice + " was removed at index " + itemIndex);
                newBag.remove(choice);
                printBagContents(newBag);
                continue;
            } else if (choice.equals("N") || choice.equals("n")) {
                break;
            } else {
                System.out.println("Please make a valid selection of Y or N.");
                continue;
            }
        }
    }

    //counts items in the bag
    public static void countInBag(Suitcase<Object> newBag, Scanner scnr) {
        Object choice;

        while (true) {
            System.out.println("Do you want to count the instances of an item in the bag?  Y/N");
            choice = scnr.next();

            if (choice.equals("Y") || choice.equals("y")){
                System.out.println("Enter the item and we will count how often it appears in the bag: ");
                choice = scnr.next();
                System.out.println("Your item is found " + newBag.countItemAmount(choice) + " times in the bag.");
                continue;
            } else if (choice.equals("N") || choice.equals("n")) {
                break;
            } else {
                System.out.println("Please make a valid selection of Y or N.");
                continue;
            }
        }
    }
}