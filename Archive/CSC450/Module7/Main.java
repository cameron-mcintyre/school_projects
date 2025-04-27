package CSC450.Module7;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<Employee>();

        employees.add(new Employee(1, 80000));
        employees.add(new Employee(2, 92000));
        employees.add(new Employee(1, 76000));
        employees.add(new Employee(1, 87000));
        
        System.out.println("Salaries are: ");

        for(int i = 0; i < employees.size(); i++){
            Employee.bonus = employees.get(1).getBonus(); //oops!
            System.out.println("\n" + i + ": " + (employees.get(i).getSalary() * Employee.bonus));
        }
        
        scnr.close();
    }
}
