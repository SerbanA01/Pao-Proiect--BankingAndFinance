package daoservices;

import model.accounts.Account;
import model.accounts.CheckingAccount;
import model.accounts.SavingsAccount;
import dao.CheckingAccountDao;
import dao.SavingsAccountDao;


public class AccountsRepoService {
    private CheckingAccountDao checkingAccountDao;
    private SavingsAccountDao savingsAccountDao;

    public AccountsRepoService() {
        this.checkingAccountDao = new CheckingAccountDao();
        this.savingsAccountDao = new SavingsAccountDao();
    }

    public CheckingAccount getCheckingAccount(String accountNumber) {
        CheckingAccount checkingAccount = checkingAccountDao.read(accountNumber);
        if(checkingAccount == null) {
            System.out.println("Checking Account not found");
            return null;
        }
        System.out.println("Checking Account found: " + checkingAccount.getAccountHolder() + " " + checkingAccount.getAccountNumber());
        return checkingAccount;

    }

    public SavingsAccount getSavingsAccount(String accountNumber) {
        SavingsAccount savingsAccount = savingsAccountDao.read(accountNumber);
        if(savingsAccount == null) {
            System.out.println("Savings Account not found");
            return null;
        }
        System.out.println("Savings Account found: " + savingsAccount.getAccountHolder() + " " + savingsAccount.getAccountNumber());
        return savingsAccount;
    }

    public void removeAccount(String accountType, String accountNumber) {

        Account account = getAccount(accountType, accountNumber);
        if(account == null) {
            System.out.println("Account not found");
            return;
        }
        switch (account) {
            case CheckingAccount checkingAccount -> checkingAccountDao.delete(checkingAccount);
            case SavingsAccount savingsAccount -> savingsAccountDao.delete(savingsAccount);
            default -> throw new IllegalStateException("Unexpected value: " + account);
        }
    }

    public void addAccount(Account account) {
        if(account == null) {
            System.out.println("Account not found");
            return;
        }
        switch (account) {
            case CheckingAccount checkingAccount -> checkingAccountDao.create(checkingAccount);
            case SavingsAccount savingsAccount -> savingsAccountDao.create(savingsAccount);
            default -> throw new IllegalStateException("Unexpected value: " + account);
        }
        System.out.println("Added " + account.getAccountHolder()+ " to the database");
    }

    public Account getAccount(String accountType, String accountNumber) {
        if(accountType.equals("checking")) {
            return getCheckingAccount(accountNumber);
        }
        if(accountType.equals("savings")) {
            return getSavingsAccount(accountNumber);
        }
        return null;
    }
}
