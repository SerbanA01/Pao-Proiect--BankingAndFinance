package daoservices;

import model.accounts.Account;
import dao.CheckingAccountDao;
import dao.SavingsAccountDao;
import model.accounts.CheckingAccount;
import model.accounts.SavingsAccount;
import others.AuditManagement;

import java.sql.SQLException;


public class AccountsRepoService {

    private CheckingAccountDao checkingAccountDao = CheckingAccountDao.getInstance();
    private SavingsAccountDao savingsAccountDao = SavingsAccountDao.getInstance();

    public AccountsRepoService() throws SQLException {}

    public SavingsAccount getSavingsAccountByNumber(String accountNumber) throws SQLException {
        SavingsAccount savingsAccount = savingsAccountDao.read(accountNumber);
        if(savingsAccount != null){
            System.out.println(savingsAccount);
        }else {
            System.out.println("No savings account with this number");
        }

        return savingsAccount;
    }

    public CheckingAccount getCheckingAccountByNumber(String accountNumber) throws SQLException {
        CheckingAccount checkingAccount = checkingAccountDao.read(accountNumber);
        if(checkingAccount != null){
            System.out.println(checkingAccount);
        }else {
            System.out.println("No checking account with this number");
        }
        return checkingAccount;
    }
    //i created 2 function that get the account but without printing it
    public SavingsAccount updateSavingsAccountByNumber(String accountNumber) throws SQLException {
        SavingsAccount savingsAccount = savingsAccountDao.read(accountNumber);
        if(savingsAccount != null){
            return savingsAccount;
        }else {
            System.out.println("No savings account with this number");
        }

        return null;
    }

    public CheckingAccount updateCheckingAccountByNumber(String accountNumber) throws SQLException {
        CheckingAccount checkingAccount = checkingAccountDao.read(accountNumber);
        if(checkingAccount != null){
            return checkingAccount;
        }else {
            System.out.println("No checking account with this number");
        }
        return null;
    }

    public void removeAccount(String typeOfAccount, String accountNumber) throws SQLException {
        Account account = getAccount(typeOfAccount, accountNumber);
        if (account == null) return;

        switch (account){
            case CheckingAccount checkingAccount -> checkingAccountDao.delete(checkingAccount);
            case SavingsAccount savingsAccount -> savingsAccountDao.delete(savingsAccount);
            default -> throw new IllegalStateException("Unexpected value: " + account);
        }
        System.out.println("Removed " + account);
    }

    public void addAccount(Account account) throws SQLException {
        if(account != null){
            switch (account){
                case CheckingAccount checkingAccount-> checkingAccountDao.add(checkingAccount);
                case SavingsAccount savingsAccount -> savingsAccountDao.add(savingsAccount);
                default -> throw new IllegalStateException("Unexpected value: " + account);
            }
        }
    }

    public Account getAccount(String typeOfAccount, String accountNumber) {
        Account account = null;
        try {
            if(typeOfAccount.equals("checking")){
                account = getCheckingAccountByNumber(accountNumber);
            }else{
                account = getSavingsAccountByNumber(accountNumber);
            }
            if(account == null) {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }

        return account;
    }

    public void updateAccount(Account account) throws SQLException {
        if(account != null){
            switch (account){
                case CheckingAccount checkingAccount -> checkingAccountDao.update(checkingAccount);
                case SavingsAccount savingsAccount -> savingsAccountDao.update(savingsAccount);
                default -> throw new IllegalStateException("Unexpected value: " + account);
            }
        }
    }
}
