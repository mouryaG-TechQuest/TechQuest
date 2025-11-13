package Day4;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.util.*;

import javax.security.auth.login.AccountNotFoundException;
public class Banking {
    private Map<String, Account> accounts;
    public Banking() {
        accounts = new HashMap<>();
    }
    public void addAccount(Account account){
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account added: " + account.getAccountNumber());
    }
    public Account getAccount(String accountNumber){
        if(!accounts.containsKey(accountNumber)){
            try {
                throw new AccountNotFoundException("Account not found: " + accountNumber);
            } catch (AccountNotFoundException e) {
                e.printStackTrace();
            }
        }
        return accounts.get(accountNumber);
    }
    public void displayAllAccounts(){
        for(Account account : accounts.values()){
            account.displayDetails();
            System.out.println("-----------------------");
        }
    }
    public void removeAccount(String accountNumber) throws AccountNotFoundException {
        if(accounts.containsKey(accountNumber)){
            accounts.remove(accountNumber);
            System.out.println("Account removed: " + accountNumber);
        } else {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
    }


    public static void main(String[] args) {
        Banking bank = new Banking();

        // Using Parent reference to child 
        Account sa = new SavingsAccount("SA123", 1000.0, "Alice", 5.0, 500.0);
        Account ca = new CheckingAccount("CA123", 2000.0, "Bob", 300.0);

        bank.addAccount(sa);
        bank.addAccount(ca);

        bank.displayAllAccounts();

        ((SavingsAccount)sa).applyInterest();

        // Exception raising example
        try {
            sa.withdraw(3000);
        } catch (InsufficientFundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Calling Overridden methods
        try {
            sa.withdraw(400);
        } catch (InsufficientFundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            
        }

        // Exceptinos raising example
        try {
            ca.withdraw(10000);
        } catch (InsufficientFundsException | InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            ca.withdraw(2200);
        } catch (InsufficientFundsException | InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        bank.displayAllAccounts();

        // Exception raising example
        try {
            bank.removeAccount("SA125");
        } catch (AccountNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            bank.removeAccount("SA123");
        } catch (AccountNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // Serialization and Deserialization can be added here 
        // Serialzing all the obejects before displaying final state
         try (FileOutputStream fos = new FileOutputStream("bank_data.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(bank.accounts);
            System.out.println("Bank data serialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserializing and display
        try (FileInputStream fis = new FileInputStream("bank_data.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Map<String, Account> deserializedAccounts = (Map<String, Account>) ois.readObject();
            System.out.println("\nBank data deserialized successfully.\n");

            for (Account account : deserializedAccounts.values()) {
                account.displayDetails();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
