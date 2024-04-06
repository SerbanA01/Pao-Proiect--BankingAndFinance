package daoservices;

import model.Accounts.Account;
import model.Accounts.CheckingAccount;
import model.Accounts.SavingsAccount;
import dao.CheckingAccountDao;
import dao.SavingsAccountDao;


public class AccountsRepoService {
    private CheckingAccountDao checkingAccountDao;
    private SavingsAccountDao savingsAccountDao;

    public AccountsRepoService(CheckingAccountDao checkingAccountDao, SavingsAccountDao savingsAccountDao) {
        this.checkingAccountDao = checkingAccountDao;
        this.savingsAccountDao = savingsAccountDao;
    }

    public CheckingAccount getCheckingAccount(String accountNumber) {
        CheckingAccount checkingAccount = checkingAccountDao.read(accountNumber);
        if(checkingAccount == null) {
            System.out.println("Checking Account not found");
            return null;
        }
        System.out.println("Checking Account found: " + checkingAccount);
        return checkingAccount;

    }

    public SavingsAccount getSavingsAccount(String accountNumber) {
        SavingsAccount savingsAccount = savingsAccountDao.read(accountNumber);
        if(savingsAccount == null) {
            System.out.println("Savings Account not found");
            return null;
        }
        System.out.println("Savings Account found: " + savingsAccount);
        return savingsAccount;
    }

    public void removeAccount(Account account) {
        if(account == null) {
            System.out.println("Account not found");
            return;
        }
        switch (account) {
            case CheckingAccount checkingAccount -> checkingAccountDao.delete(checkingAccount);
            case SavingsAccount savingsAccount -> savingsAccountDao.delete(savingsAccount);
            default -> throw new IllegalStateException("Unexpected value: " + account);
        }
        System.out.println("Removed " + account);
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
        System.out.println("Added " + account);
    }
}
