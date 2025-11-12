package Day2;
import java.util.*;
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
        return accounts.get(accountNumber);
    }
    public void displayAllAccounts(){
        for(Account account : accounts.values()){
            account.displayDetails();
            System.out.println("-----------------------");
        }
    }
    public void removeAccount(String accountNumber){
        if(accounts.containsKey(accountNumber)){
            accounts.remove(accountNumber);
            System.out.println("Account removed: " + accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
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
        // Calling Overridden methods
        sa.withdraw(400);
        ca.withdraw(2200);

        bank.displayAllAccounts();

        bank.removeAccount("SA123");
        bank.displayAllAccounts();
    }
}
