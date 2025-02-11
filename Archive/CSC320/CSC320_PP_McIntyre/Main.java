package CSC320_PP_McIntyre;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Main{

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        ArrayList<Car> inventory = new ArrayList<Car>();
        int choice;
        String exit;

        Car.addDefaultCars(inventory);

        while(true){
            choice = Main.selection(scnr);
            switch(choice){
                case 1: System.out.println("--Display Inventory--"); Main.displayInventory(inventory, scnr); break;
                case 2: System.out.println("--Add a Vehicle--"); Main.addCar(inventory, scnr); break;
                case 3: System.out.println("--Modify a Vehicle--"); Main.modifyCar(inventory, scnr); break;
                case 4: System.out.println("--Find a Vehicle--"); Main.findCar(inventory, scnr); break;
                case 5: System.out.println("--Remove a Vehicle--"); Main.removeCar(inventory, scnr); break;
                case 6: System.out.println("--Closing Menu--"); break;
            }
            System.out.println("Do you want to exit? TYPE 'Y' TO EXIT OR ANYTHING ELSE TO CONTINUE");
            exit = scnr.next();
            if(exit.equalsIgnoreCase("Y")){
                break;
            }
        }
        System.out.println("Thanks for using the car inventory program.");
        scnr.close();
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //This method provides the selection capability in the main.
    public static int selection(Scanner scnr){
        int choice;
        int numOfOptions = 7; //The number of menu items plus one.

        System.out.println("");
        System.out.println("Welcome to Cam's Car Dealership inventory system!  Please select an option:");
        System.out.println("");
        System.out.println("[1]: Display the inventory (file or onscreen)");
        System.out.println("[2]: Add a vehicle");
        System.out.println("[3]: Modify a vehicle");
        System.out.println("[4]: Find a vehicle");
        System.out.println("[5]: Remove a vehicle from the database");
        System.out.println("[6]: Close menu");

        while(true){
            try{
                System.out.println("Please enter your selection: ");
                choice = scnr.nextInt();

                if(choice > 0 && choice < numOfOptions){break;}
                else{System.out.println("Please enter a valid selection (1 through 6)!");}

            } catch(InputMismatchException errorMessage){
                System.out.println("Please enter a valid number as a single number digit! " + errorMessage.getMessage());
                scnr.next();
            }
        }
        return choice;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //This method provides displays of the entire inventory, either as a file or on the console.
    public static void displayInventory(ArrayList<Car> inventory, Scanner scnr){
        int i;
        int choice;
        String continueVar;
        String[] output;

        try{
            System.out.println("Do you want to write to a file, or display inventory on the screen?");
            System.out.println("[1] = write to file");
            System.out.println("[2] = display on screen");
            System.out.println("Please enter your choice: ");

            choice = scnr.nextInt();
    
            if(choice == 1){
                System.out.println("Are you sure you want to print to a file?  Y or N: ");
                continueVar = scnr.next();
    
                if(continueVar.equalsIgnoreCase("Y")){
                    Car.writeCarDetails(inventory);
                } else if(continueVar.equalsIgnoreCase("N")) {
                    System.out.println("Cancelling file write.");
                } else {
                    System.out.println("Invalid entry.");
                }
            }
    
            else if(choice == 2){
                for(i = 0; i < inventory.size(); i++){
                    System.out.println("Item Index: " + i);
                    output = inventory.get(i).displayCarDetails(inventory, i);
                    System.out.println(Arrays.toString(output));
                    System.out.println("");
                }
            }
            else{
                System.out.println("Invalid input entered.  You need to enter either 1 or 2.");
            }
        } catch (InputMismatchException error){
            System.out.println("Invalid entry: "+ error.getMessage());
            scnr.next();
        }  
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //This method allows addition of new cars to the arraylist.
    public static void addCar(ArrayList<Car> inventory, Scanner scnr){
        Car newCar = new Car();
        int index;

        try{
            System.out.println("Enter make: ");
            newCar.setCarMake(scnr.next());
    
            System.out.println("Enter model: ");
            newCar.setCarModel(scnr.next());
    
            System.out.println("Enter color (1=blue, 2=red, 3=silver, 4=yellow, 5=black, 6=white, 7=other): ");
            newCar.setCarColor(scnr.nextInt());
    
            System.out.println("Enter condition (1=excellent, 2=good, 3=fair, 4=used, 5=other): ");
            newCar.setCarCondition(scnr.nextInt());
    
            System.out.println("Enter year: ");
            newCar.setCarYear(scnr.nextInt());
    
            System.out.println("Enter mileage: ");
            newCar.setCarMileage(scnr.nextInt());
    
            System.out.println("Enter price: ");
            newCar.setCarPrice(scnr.nextDouble());
    
            System.out.println("Here's the new car: ");
            System.out.println("Make: " + newCar.getCarMake() + ", Model: " + newCar.getCarModel() + ", Color: " + Car.numberToColor(newCar.getCarColor()) + ", Condition: " + Car.numberToCondition(newCar.getCarCondition()) + ", Year: " + newCar.getCarYear() + ", Mileage: " + newCar.getCarMileage() + ", Price: " + newCar.getCarPrice());
    
            inventory.add(newCar);
            index = inventory.indexOf(newCar);
            System.out.println("New car index is: " + index);
        } catch (InputMismatchException error){
            System.out.println("");
            System.out.println("You need to enter appropriate values! " + error.getMessage());
            System.out.println("");
        }
        
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //This method removes an item from the arraylist.
    public static void removeCar(ArrayList<Car> inventory, Scanner scnr){
        int index;

        System.out.println("");
        System.out.println("This module enables you to remove a vehicle from the database.");
        System.out.println("Please enter the index of the vehicle you would like to remove.  Warning!  This cannot be undone.");

        try{
            index = scnr.nextInt();
            if(index >= 0 && index < inventory.size()){
                inventory.remove(index);
                System.out.println("Item index " + index + " has been removed.");
                System.out.println("");
            } else { 
                System.out.println("You entered an index value that's not in the database! ");
                System.out.println("");
            } 
        } catch (ArrayIndexOutOfBoundsException error){
            System.out.println("You entered a value that's not an index in the database! " + error.getMessage());
        }
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //This method allows editing of a selected item in the arraylist.
    public static void modifyCar(ArrayList<Car> inventory, Scanner scnr){
        int index;
        int choice;
        String make;
        String model;
        int color;
        int condition;
        int year;
        int mileage;
        double price;
        String[] output;

        System.out.println("");
        System.out.println("This module enables you to edit a vehicle in the database.");
        System.out.println("Please enter the index of the vehicle you would like to edit.");
        index = scnr.nextInt();

        try{
            if(index >= 0 && index < inventory.size()){
                System.out.println("Here are the details for the vehicle you selected: ");
                output = inventory.get(index).displayCarDetails(inventory, index);
                System.out.println(Arrays.toString(output));
                System.out.println("");
                System.out.println("What attribute would you like to modify?");
                System.out.println("[1] = Car make");
                System.out.println("[2] = Car model");
                System.out.println("[3] = Car color");
                System.out.println("[4] = Car condition");
                System.out.println("[5] = Car year");
                System.out.println("[6] = Car mileage");
                System.out.println("[7] = Car price");
                System.out.println("");

                choice = scnr.nextInt();
                switch(choice){
                    case 1:
                        System.out.println("Enter the new make of the car: ");
                        make = scnr.next();
                        inventory.get(index).setCarMake(make);
                        break;
                    case 2:
                        System.out.println("Enter the new model of the car: ");
                        model = scnr.next();
                        inventory.get(index).setCarModel(model);
                        break;
                    case 3:
                        System.out.println("Enter the new color of the car (1=blue, 2=red, 3=silver, 4=yellow, 5=black, 6=white, 7=other): ");
                        color = scnr.nextInt();
                        inventory.get(index).setCarColor(color);
                        break;
                    case 4:
                        System.out.println("Enter the new condition of the car (1=excellent, 2=good, 3=fair, 4=used, 5=other): ");
                        condition = scnr.nextInt();
                        inventory.get(index).setCarCondition(condition);
                        break;
                    case 5:
                        System.out.println("Enter the new year of the car: ");
                        year = scnr.nextInt();
                        inventory.get(index).setCarYear(year);
                        break;
                    case 6:
                        System.out.println("Enter the new mileage of the car: ");
                        mileage = scnr.nextInt();
                        inventory.get(index).setCarMileage(mileage);
                        break;
                    case 7:
                        System.out.println("Enter the new price of the car: ");
                        price = scnr.nextDouble();
                        inventory.get(index).setCarPrice(price);
                        break;
                }

                System.out.println("");
                System.out.println("Here are the updated details for the vehicle you selected: ");
                System.out.println("");
                output = inventory.get(index).displayCarDetails(inventory, index);
                System.out.println(Arrays.toString(output));
                System.out.println("");
            } else {
                System.out.println("You entered a value that's not in the database! ");
            }
        } catch (IndexOutOfBoundsException error){
            System.out.println("You entered a value that's not in the database! " + error.getMessage());
        } 
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //This method allows a user to find any cars of a specific make and model.
    public static void findCar(ArrayList<Car> inventory, Scanner scnr){
        int i;
        String make;
        String model;
        String[] output;
        
        try{
            System.out.println("This method enables the user to find a particular group of cars of the same make and model.");
            
            System.out.println("Please enter the make of the cars you'd like to find: ");
            make = scnr.next();
    
            System.out.println("Please enter the model of the cars you'd like to find: ");
            model = scnr.next();
    
            System.out.println("Printing all results that match your parameters: ");
            System.out.println("");
            for(i = 0; i < inventory.size(); i++){
                if(inventory.get(i).getCarMake().equals(make) && inventory.get(i).getCarModel().equals(model)){
                    output = inventory.get(i).displayCarDetails(inventory, i);
                    System.out.println(Arrays.toString(output));
                }
            }
            System.out.println("");
            System.out.println("End of list.");
        } catch (InputMismatchException error){
            System.out.println("You entered some kind of incorrect inputs. " + error.getMessage());
        }
    }
}