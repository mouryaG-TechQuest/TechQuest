package Day3;
public class CheckingAccount extends Account{
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, String accountHolderName, double overdraftLimit) {
        super(accountNumber, balance, accountHolderName, "Checking");
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
    // Override methods 
    @Override 
    public void withdraw(double amount) throws InsufficientFundsException{
        if(amount > 0 && amount <= (getBalance() + overdraftLimit)){
            setBalance(getBalance() - amount);
            System.out.println("Withdrew: " + amount);
        } else {
            throw new InsufficientFundsException("Exceeded overdraft limit or invalid amount.");
        }
    }

    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
    
}
