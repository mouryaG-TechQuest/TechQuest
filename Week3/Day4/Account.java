package Day4;
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

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    private void displayDetails(){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    private void transfer(Account toAccount, double amount){
        if(amount > 0 && amount <= this.balance){
            this.balance -= amount;
            toAccount.balance += amount;
            System.out.println("Transferred " + amount + " to " + toAccount.accountHolderName);
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

    // Main method for testing
    public static void main(String[] args) throws InsufficientExcpetion {
        Scanner scanner = new Scanner(System.in);

        // Create an account
        Account myAccount = new Account("123456", "John Doe", 1000.0);
        Account myAccount2=new Account("654321","Jane Doe",500.0);
        // Display initial balance
        System.out.println("Initial Balance: " + myAccount.getBalance());

        // Deposit money
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        myAccount.deposit(depositAmount);
        System.out.println("Balance after deposit: " + myAccount.getBalance());

        // Withdraw money
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        myAccount.withdraw(withdrawAmount);
        System.out.println("Balance after withdrawal: " + myAccount.getBalance());



        // Display account details      myAccount.displayDetails();
        myAccount2.displayDetails();
        
        myAccount.transfer(myAccount2,200);
        System.out.println("Balance after transfer from myaccount 1: " + myAccount.getBalance());
        
        
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(myAccount);    
        accounts.add(myAccount2);

        accounts.forEach(acc -> acc.displayDetails());  

        System.out.println("Enter account number to search: ");
        String searchAccountNumber = scanner.next();
        boolean found = false;  
        for (Account acc : accounts) {
            if (acc.accountNumber.equals(searchAccountNumber)) {
                acc.displayDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            try {
                throw new accountNotFoundException("Account with number " + searchAccountNumber + " not found.");
            } catch (accountNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        
        
        
        scanner.close();


    }
}