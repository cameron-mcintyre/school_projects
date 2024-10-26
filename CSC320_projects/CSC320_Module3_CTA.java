import java.util.Scanner;

public class CSC320_Module3_CTA {
    
    public static void main(String[] args){
        
        Scanner getinfo = new Scanner(System.in);
                
        int income = 0;
        int incomecase = 0;

        System.out.println("Welcome of the tax calculator! ");
        System.out.println("Please enter your weekly income:");

        income = getinfo.nextInt();
        
        if (income < 0){
            incomecase = 999;
        }
        else {
            incomecase = income/500;
        }

        switch (incomecase){

            case 0:
                System.out.println("Your tax rate is 10%");
                break;
            case 1:
            case 2:
                System.out.println("Your tax rate is 15%");
                break;
            case 3:
            case 4:
                System.out.println("Your tax rate is 20%");
                break;
            case 999:
                System.out.println("You had a horrible week and made negative money.  Your tax rate is 0%");
                break;
            default:
                System.out.println("Your tax rate is 30%");
                break;
        }
           
        getinfo.close();
    }
}
