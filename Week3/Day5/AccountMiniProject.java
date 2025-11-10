package Day5;
import Day5.AccountClasses;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountMiniProject extends Exception {
    public static void main(String[] args) throws InsufficientExcpetion {
        ArrayList<AccountClasses> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Sample accounts
        accounts.add(new AccountClasses("001", "Alice", 1000));
        accounts.add(new AccountClasses("002", "Bob", 1500));
        accounts.add(new AccountClasses("003", "Charlie", 2000));
        accounts.add(new AccountClasses("004", "Diana", 2500)); 
        // Display account details
        for (AccountClasses acc : accounts) {
            acc.displayDetails();
            System.out.println("-----");
        }

        // Example transfer
        try {
            AccountClasses fromAccount = accounts.get(0); // Alice
            AccountClasses toAccount = accounts.get(1);   // Bob
            fromAccount.transfer(toAccount, 200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Display updated account details
        for (AccountClasses acc : accounts) {
            acc.displayDetails();
            System.out.println("-----");
        }
        System.out.println("Enter account number to fetch account");
            String searchAccountNumber = scanner.next();
            boolean found = false;  
            AccountClasses account=null;
            for (AccountClasses acc : accounts) {
                if (acc.getAccountNumber().equals(searchAccountNumber)) {
                    acc.displayDetails();
                    found = true;
                    account=acc;
                    break;
                }
            }
            if (!found) {
                System.out.println("Account with number " + searchAccountNumber + " not found.");
            }
        while(true){
            System.out.println("Enter 1.) Deposit 2.) Withdraw 3.) Transfer 4.) Exit");
            int choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Balance after deposit: " + account.getBalance());
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw: ");       
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    System.out.println("Balance after withdrawal: " + account.getBalance());
                    break;
                case 3:
                    System.out.println("Enter target account number for transfer: ");
                    String targetAccountNumber = scanner.next();
                    AccountClasses targetAccount = null;
                    for (AccountClasses acc : accounts) {
                        if (acc.getAccountNumber().equals(targetAccountNumber)) {
                            targetAccount = acc;
                            break;
                        }
                    }   
                    if (targetAccount != null) {
                        System.out.println("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(targetAccount, transferAmount);
                        System.out.println("Balance after transfer: " + account.getBalance());
                    } else {
                        System.out.println("Target account not found.");
                    }   
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    break;
            }
        }
    }
}