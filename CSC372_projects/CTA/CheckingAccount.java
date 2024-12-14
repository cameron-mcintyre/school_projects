package CSC372_projects.CTA;

//The CheckingAccount class inherits from the BankAccount class.
public class CheckingAccount extends BankAccount {
    
    protected double interestRate = 1.02;

    //Getter and Setter of the interest rate for the CheckingAccount class.
    public void setInterestRate(Double newInterestRate){this.interestRate = newInterestRate;}
    public Double getInterestRate(){return interestRate;}
    
    @Override //This method will override the withdrawal method in the base class and also compute overdrafting.
    public void processWithdrawal(double newWithdrawal){
        super.processWithdrawal(newWithdrawal);
        if (getBalance() < 0){
            System.out.println("Negative balance!  Overdraft fee added!");
            balance = balance - 30.0;
        }
    }

    @Override //This method overrides the base class summary and also prints the interest rate.
    public void accountSummary(){
        super.accountSummary();
        System.out.println("Interest Rate: " + interestRate);
    }
}
