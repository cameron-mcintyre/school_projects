package CSC450.Module7;

public class Employee {

    double salary;
    int empType;
    static double bonus = 1.2;

    public Employee(int empType, double salary){
        this.empType = empType;
        this.salary = salary;
    }

    public double getSalary(){
        return salary;
    }

    public double getBonus(){
        if(empType==2){
            return 2.3;
        } else {
            return 1.2;
        }
    }
}