package Day5;

import java.util.ArrayList;
import java.util.Scanner;

class InsufficientExcpetion extends Exception {
    public InsufficientExcpetion(String message) {
        super(message);
    }
}

class accountNotFoundException extends Exception {
    public accountNotFoundException(String message) {
        super(message);
    }
}

public class AccountClasses extends Exception {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Constructor
    AccountClasses(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public void displayDetails(){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    public void transfer(AccountClasses toAccount, double amount){
        if(amount > 0 && amount <= this.balance){
            this.balance -= amount;
            toAccount.balance += amount;
            System.out.println("Transferred " + amount + " to " + toAccount.getAccountHolderName());
        } else {
            System.out.println("Transfer failed. Check the amount and balance.");
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) throws InsufficientExcpetion {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else {
            throw new InsufficientExcpetion("Insufficient balance for withdrawal.");
        }
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }

    
}