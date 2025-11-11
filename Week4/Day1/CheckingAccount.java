package Day1;
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

    @Override
    public void withdraw(double amount){
        if(amount > 0 && amount <= (getBalance() + overdraftLimit)){
            setBalance(getBalance() - amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Exceeded overdraft limit or invalid amount.");
        }
    }

    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
    
}
