package Day2;
public class SavingsAccount extends Account{
    private double interestRate;
    private double withdrawLimit;

    public SavingsAccount(String accountNumber, double balance, String accountHolderName, double interestRate, double withdrawLimit) {
        super(accountNumber, balance, accountHolderName, "Savings");
        this.interestRate = interestRate;
        this.withdrawLimit = withdrawLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public void applyInterest(){
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
        System.out.println("Applied interest: " + interest);
    }
    public void withdraw(double amount){
        if(amount > 0 && amount <= getBalance() && amount <= withdrawLimit){
            setBalance(getBalance() - amount);
            System.out.println("Withdrew: " + amount);
        } else if(amount > withdrawLimit){
            System.out.println("Exceeded withdraw limit of: " + withdrawLimit);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Withdraw Limit: " + withdrawLimit);
    }
    
}
