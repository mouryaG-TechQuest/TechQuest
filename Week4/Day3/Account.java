package Day3;
import java.util.*;

class Account{
    private String accountNumber;
    private double balance; 
    private String accountHolderName;
    private String accountType;
    public Account(String accountNumber, double balance, String accountHolderName, String accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public void displayDetails(){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException, InvalidInputException{
        if(amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else {
            throw new InsufficientFundsException("Insufficient balance or invalid amount.");
        }
    }

    public void transfer(Account toAccount, double amount) throws InsufficientFundsException, InvalidInputException{
        if(amount > 0 && amount <= balance){
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred: " + amount + " to Account Number: " + toAccount.getAccountNumber());
        } else {
            System.out.println("Insufficient balance or invalid amount for transfer.");
        }
    }

    
    
}