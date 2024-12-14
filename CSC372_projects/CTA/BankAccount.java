package CSC372_projects.CTA;

//This is the base class, BankAccount.
public class BankAccount {
    
    protected String firstName;
    protected String lastName;
    protected int accountID;
    protected double balance;

    public BankAccount(){
        this.firstName = "";
        this.lastName = "";
        this.accountID = -1;
        this.balance = 0.0;
    }

    public void deposit(double depositAmount){
        balance = balance + depositAmount;
    }

    public void processWithdrawal(double withdrawalAmount){
        balance = balance - withdrawalAmount;
    }

    //Getters and Setters for the various items in the class.
    public void setFirstName(String newFirstName){this.firstName = newFirstName;}
    public String getFirstName(){return firstName;}
    public void setLastName(String newLastName){this.lastName = newLastName;}
    public String getLastName(){return lastName;}
    public void setAccountID(Integer newAccountID){this.accountID = newAccountID;}
    public int getAccountID(){return accountID;}
    public double getBalance(){return balance;}

    public void accountSummary(){
        System.out.println("Account ID: " + accountID);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Current Balance: " + balance);
    }
}
