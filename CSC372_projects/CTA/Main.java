package CSC372_projects.CTA;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        String newFirstName;
        String newLastName;
        int newAccountID;
        double deposit;
        double withdrawal;

        System.out.println("Welcome to the new bank account creation tool!\n");

        //Getting inputs for the bank account information.
        System.out.println("Please enter the new account holder's first name: ");
        newFirstName = scnr.next();

        System.out.println("Please enter the new account holder's last name: ");
        newLastName = scnr.next();

        System.out.println("Please enter the new account holder's account ID: ");
        newAccountID = scnr.nextInt();
        System.out.println(" ");

        //Comment out the account type you don't wish to create.
        //BankAccount newAccount = new BankAccount();
        CheckingAccount newAccount = new CheckingAccount();
        
        newAccount.setFirstName(newFirstName);
        newAccount.setLastName(newLastName);
        newAccount.setAccountID(newAccountID);
        
        //Reading back the new information.
        newAccount.accountSummary();
        System.out.println(" ");

        System.out.println("Please enter the amount of money to deposit: ");
        deposit = scnr.nextDouble();
        newAccount.deposit(deposit);
        System.out.println(" ");

        System.out.println("Please enter the amount to withdrawal: ");
        withdrawal = scnr.nextDouble();
        newAccount.processWithdrawal(withdrawal);
        System.out.println(" ");

        //Reading back the information that has been updated.
        newAccount.accountSummary();

        scnr.close();
    }
}
