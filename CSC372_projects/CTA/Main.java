package CSC372_projects.CTA;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        ArrayList<BankAccount> allAccounts = new ArrayList<BankAccount>();

        String newFirstName;
        String newLastName;
        int newAccountID;
        double deposit;
        double withdrawal;

        System.out.println("Welcome to the new bank account creation tool!\n");

        System.out.println("Please enter the new account holder's first name: ");
        newFirstName = scnr.next();

        System.out.println("Please enter the new account holder's last name: ");
        newLastName = scnr.next();

        System.out.println("Please enter the new account holder's account ID: ");
        newAccountID = scnr.nextInt();
        System.out.println(" ");

        CheckingAccount newAccount = new CheckingAccount();
        newAccount.setFirstName(newFirstName);
        newAccount.setLastName(newLastName);
        newAccount.setAccountID(newAccountID);

        allAccounts.add(newAccount);
        
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

        newAccount.accountSummary();

        scnr.close();

    }
}
