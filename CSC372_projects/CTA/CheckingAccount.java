package CSC372_projects.CTA;

public class CheckingAccount extends BankAccount {
    
    protected double interestRate = 1.02;

    public void setInterestRate(Double newInterestRate){
        this.interestRate = newInterestRate;
    }

    public Double getInterestRate(){
        return interestRate;
    }
    
    @Override
    public void processWithdrawal(double newWithdrawal){
        
        super.processWithdrawal(newWithdrawal);
        
        if (getBalance() < 0){
            System.out.println("Negative balance!  Overdraft fee added!");
            balance = balance - 30.0;
        }
    }

    public void displayAccount(){
        super.accountSummary();
        System.out.println("Interest Rate: " + interestRate);
    }
}
