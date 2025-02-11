package CSC320_PP_McIntyre;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Car {
    
    //Car object parameters.
    public final String carDealership = "Cam's Cars";
    private String carMake;
    private String carModel;
    private int carColor; //1=blue, 2=red, 3=silver, 4=yellow, 5=black, 6=white, 7=N/A etc
    private int carCondition; // 1=excellent, 2=good, 3=fair, 4=used, 5=N/A
    private int carYear;
    private int carMileage;
    private double carPrice;

    //Default constructor.
    public Car() {
        carMake = "";
        carModel = "";
        carColor = 7;
        carCondition = 5;
        carYear = -1;
        carMileage = -1;
        carPrice = -1;
    }

    //Basic parameterized constructor - I never ended up using this one.
    public Car(String make, String model, int color, int year) {
        this.carMake = make;
        this.carModel = model;
        this.carColor = color;
        this.carYear = year;
    }
    
    //Normal parameterized constructor.  This is the one I use.
    public Car(String make, String model, int color, int condition, int year, int miles, double price) {
        this.carMake = make;
        this.carModel = model;
        this.carColor = color;
        this.carCondition = condition;
        this.carYear = year;
        this.carMileage = miles;
        this.carPrice = price;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Getters and setters for car make attribute.
    public String getCarMake(){return carMake;}
    public void setCarMake(String carMake){this.carMake = carMake;}
    
    //Getters and setters for car model attribute.
    public String getCarModel(){return carModel;}
    public void setCarModel(String carModel){this.carModel = carModel;}
    
    //Getters and setters for car color attribute.
    public int getCarColor(){return carColor;}
    public void setCarColor(int carColor){this.carColor = carColor;}
    
    //Getters and setters for car condition attribute.
    public int getCarCondition(){return carCondition;}
    public void setCarCondition(int carCondition){this.carCondition = carCondition;}
    
    //Getters and setters for car year attribute.
    public int getCarYear(){return carYear;}
    public void setCarYear(int carYear){this.carYear = carYear;}
    
    //Getters and setters for car mileage attribute.
    public int getCarMileage(){return carMileage;}
    public void setCarMileage(int carMileage){this.carMileage = carMileage;}
    
    //Getters and setters for car price attribute.
    public double getCarPrice(){return carPrice;}
    public void setCarPrice(double carPrice){this.carPrice = carPrice;}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Display car.  This outputs a single vehicle from the arraylist.
    public String[] displayCarDetails(ArrayList<Car> inventory, int i){
        
        Car car = inventory.get(i);
        String[] carDetails = new String[]{"Make", car.getCarMake(), " Model", car.getCarModel(), " Color", numberToColor(car.getCarColor()), " Condition", numberToCondition(car.getCarCondition()), " Year", Integer.toString(car.getCarYear()), " Odometer Miles", Integer.toString(car.getCarMileage()), " Asking Price", Double.toString(car.getCarPrice())};
        return carDetails;
    }


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Writing all the car details to a file.
    public static void writeCarDetails(ArrayList<Car> inventory) {
        int i;
        FileOutputStream fileWriter = null;
        PrintWriter outputFileStream = null;

        try{
            fileWriter = new FileOutputStream("inventoryFile.txt");
            outputFileStream = new PrintWriter(fileWriter);

            for(i = 0; i < inventory.size(); i++){
                    outputFileStream.println("Item Index: " + i);
                    outputFileStream.println("Car Make: " + inventory.get(i).getCarMake() + ", Car Model: " + inventory.get(i).getCarModel() + ", Car color: " + numberToColor(inventory.get(i).getCarColor()));
                    outputFileStream.println("Car condition: " + numberToCondition(inventory.get(i).getCarCondition()) + ", Car year: " + inventory.get(i).getCarYear() + ", Car mileage: " + inventory.get(i).getCarMileage());
                    outputFileStream.println("Car Price: " + inventory.get(i).getCarPrice());
                    outputFileStream.println(""); 
            }
        } catch(IOException error){
            System.out.println("Error opening file. " + error.getMessage());  
        } finally {
            outputFileStream.close(); 
        }
        System.out.println("Write complete.");   
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Converting the color values (stored as ints) into english language colors.
    public static String numberToColor(int colorInt){
        //1=blue, 2=red, 3=silver, 4=yellow, 5=black, 6=white, 7=N/A etc
        String color = "";

        switch(colorInt){
            case 1: color = "Blue"; break;
            case 2: color =  "Red";  break;
            case 3: color =  "Silver";  break;
            case 4: color =  "Yellow"; break;
            case 5: color =  "Black"; break;
            case 6: color =  "White"; break;
            case 7: color = "Other"; break;
        }
        return color;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Converting the condition values (stored as ints) into english language conditions.
    public static String numberToCondition(int conditionInt){
        // 1=excellent, 2=good, 3=fair, 4=used, 5=N/A
        String condition = "";

        switch(conditionInt){
            case 1: condition = "Excellent"; break;
            case 2: condition =  "Good";  break;
            case 3: condition =  "Fair";  break;
            case 4: condition =  "Used"; break;
            case 5: condition = "Other"; break;
        }
        return condition;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //A quick way to populate five cars into the database.  The call to this method can be commented out in the main.
    public static void addDefaultCars(ArrayList<Car> inventory){
        Car car1 = new Car("Mazda", "3", 1, 1, 2015, 12500, 19000.00);
        Car car2 = new Car("Ford", "F150", 2, 2, 2011, 87032, 10000.00);
        Car car3 = new Car("Saturn", "LS", 3, 4, 2005, 177031, 4000.00);
        Car car4 = new Car("Porsche", "Boxster", 6, 1, 2014, 12543, 33000.00);
        Car car5 = new Car("Ford", "F150", 2, 4, 1984, 125434, 500.00);
        inventory.add(car1);
        inventory.add(car2);
        inventory.add(car3);
        inventory.add(car4);
        inventory.add(car5);

        System.out.println("Added five default cars!");
    }
}