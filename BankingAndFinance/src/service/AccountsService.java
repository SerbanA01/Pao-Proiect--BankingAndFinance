package service;

import daoservices.AccountsRepoService;
import model.Accounts.Account;
import model.Accounts.CheckingAccount;
import model.Accounts.SavingsAccount;

import java.util.Scanner;

public class AccountsService {
    private AccountsRepoService dbService;

    public AccountsService() {
        this.dbService = new AccountsRepoService();
    }

    public void create(Scanner scanner){
        System.out.println("Enter the account type [savings/checking]:");
        String accountType = scanner.nextLine().toLowerCase();
        if(!accountType.equals("savings") && !accountType.equals("checking") ) { return; }
        accountInit(scanner, accountType);
    }

    public void read(Scanner scanner){
        System.out.println("Enter the account number:");
        String accountNumber = scanner.nextLine();
        dbService.getCheckingAccount(accountNumber);
        dbService.getSavingsAccount(accountNumber);

    }

    public void delete(Scanner scanner){
        System.out.println("Enter the account number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter the account type [saving/checking]:");
        String accountType = scanner.nextLine().toLowerCase();
        if(!accountType.equals("savings") && !accountType.equals("checking") ) { return; }

        dbService.removeAccount(accountType, accountNumber);
    }

    public void update(Scanner scanner){
        System.out.println("typeOfAccount:");
        String typeOfAccount = scanner.nextLine();
        if(!typeOfAccount.equals("savings") && !typeOfAccount.equals("checking") ) { return; }
        System.out.println("Enter the account number:");
        String accountNumber = scanner.nextLine();

        System.out.println("Enter the account holder name:");
        String name = scanner.nextLine();
        System.out.println("Enter the balance:");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account = dbService.getAccount(typeOfAccount, accountNumber);
        if(account == null) { return; }
        account.setAccountHolder(name);
        account.setBalance(balance);
        account.setAccountNumber(accountNumber);
        if(typeOfAccount.equals("checking")){
            checkingAccountInit(scanner, (CheckingAccount) account);
        }
        if(typeOfAccount.equals("savings")){
            savingAccountInit(scanner, (SavingsAccount) account);
            System.out.println("Account updated successfully! Welcome " + name + " with account number: " + accountNumber);
        }


    }


    private void accountInit(Scanner scanner, String accountType){
        System.out.println("Enter the account holder name:");
        String name = scanner.nextLine();
        double balance = 0;
        //we will generate a random account number
        long accountNumber = (long) (Math.random() * 10000000000000000L);
        String accountNumberString = String.valueOf(accountNumber);

        Account account = new Account(accountNumberString, name, balance);
        if(accountType.equals("checking")){
            CheckingAccount checkingAccount = new CheckingAccount(account);
            checkingAccountInit(scanner, checkingAccount);
            account = checkingAccount;
        }
        if(accountType.equals("savings")){
            SavingsAccount savingsAccount = new SavingsAccount(account);
            savingAccountInit(scanner, savingsAccount);
            account = savingsAccount;
        }

        dbService.addAccount(account);
        System.out.println("Account created successfully! Welcome " + name + " with account number: " + accountNumberString);

    }

    /*
    * private double overdraftLimit;
    private double transactionFee;
    private String debitCardNumber;
    * */
    private void checkingAccountInit(Scanner scanner, CheckingAccount account){
        System.out.println("Enter the overdraft limit:");
        double overdraftLimit = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the transaction fee:");
        double transactionFee = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the debit card number:");
        String debitCardNumber = scanner.nextLine();
        scanner.nextLine();

        account.setOverdraftLimit(overdraftLimit);
        account.setTransactionFee(transactionFee);
        account.setDebitCardNumber(debitCardNumber);


    }

    /*
    * private double interestRate;
    private double minimumBalance;
    private double penalty;
    * */
    private void savingAccountInit(Scanner scanner, SavingsAccount account){

        System.out.println("Enter the interest rate:");
        double interestRate = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the minimum balance:");
        double minimumBalance = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the penalty:");
        double penalty = scanner.nextDouble();
        scanner.nextLine();

        account.setInterestRate(interestRate);
        account.setMinimumBalance(minimumBalance);
        account.setPenalty(penalty);
    }
}
