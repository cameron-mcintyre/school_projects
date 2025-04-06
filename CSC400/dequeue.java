package CSC400;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class dequeue {
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        LinkedList<String> train = new LinkedList<String>();

        train.add("HopperCar");
        train.add("HopperCar");
        train.add("FlatCar");
        train.add("FlatCar");
        train.add("BoxCar");
        train.add("Locomotive");

        while(true){

            System.out.println("Welcome to the train car program!");
            System.out.println("Please add a car!");
            System.out.println("1: Hopper Car");
            System.out.println("2: Flat Car");
            System.out.println("3: Box Car");
            System.out.println("4: Locomotive");
            
            try{
                int choice = scnr.nextInt();
                scnr.nextLine();
                
                switch(choice){
                    case 1: train.addFirst("HopperCar"); train.removeLast(); break;
                    case 2: train.addFirst("FlatCar"); train.removeLast(); break;
                    case 3: train.addFirst("BoxCar"); train.removeLast(); break;
                    case 4: train.addFirst("Locomotive"); train.removeLast(); break;
                    default: System.out.println("Not a car!");
                }
                System.out.println("Here comes the train!!!\n");

                for(int i = 0; i < train.size(); i++){
                    System.out.print(train.get(i) + "-");
                }

            } catch (InputMismatchException e){
                System.out.println("Please select one of the cars!");
                //e.printStackTrace();
            }
            
            System.out.println("Do you want to go again?  Y or N");
            String goAgain = scnr.next();
            
            if(goAgain.equalsIgnoreCase("N")){break;}
            scnr.nextLine();
        }  
        scnr.close();
    }
}