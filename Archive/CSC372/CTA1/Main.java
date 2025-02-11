package CSC372_projects.CTA1;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        String newFirstName;
        String newLastName;
        int accountID;
        double deposit;
        double withdrawal;
        int continueP = 1;
        ArrayList<CheckingAccount> accounts = new ArrayList<CheckingAccount>();

        
        System.out.println("Welcome to the new bank account creation tool!\n");

        while(continueP == 1){ //PP1 new code improvement

            System.out.println("Press 1 for an existing account, or press 2 to create a new account");
            continueP = scnr.nextInt();  //PP1 new code improvement

            if(continueP == 1){  //PP1 new code improvement

                System.out.println("Please enter the account ID: ");  //PP1 new code improvement
                accountID = scnr.nextInt();
                for(CheckingAccount account : accounts){
                    if (account.getAccountID() == accountID){
                        account.accountSummary();
                        break;
                    } else {
                        System.out.println("No account found!");
                    }
                }
            } else if (continueP == 2) {  //PP1 new code improvement
                
                System.out.println("Please enter the new account holder's first name: ");
                newFirstName = scnr.next();

                System.out.println("Please enter the new account holder's last name: ");
                newLastName = scnr.next();

                System.out.println("Please enter the new account holder's account ID: ");
                accountID = scnr.nextInt();
                System.out.println(" ");

                CheckingAccount newAccount = new CheckingAccount();

                newAccount.setFirstName(newFirstName);
                newAccount.setLastName(newLastName);
                newAccount.setAccountID(accountID);

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
                accounts.add(newAccount);

            } else {  //PP1 new code improvement
                System.out.println("Please make a valid selection.");
            }

            System.out.println("\nPress 1 to perform another transaction or 2 to quit: \n");
            continueP = scnr.nextInt();  //PP1 new code improvement
        }

        scnr.close();
    }
}
