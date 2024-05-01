package dao;

import java.util.List;
import java.util.ArrayList;
import model.accounts.CheckingAccount;

public class CheckingAccountDao {
    private static List<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
    public void create(CheckingAccount checkingAccount) {
        checkingAccounts.add(checkingAccount);
    }
    public void delete(CheckingAccount checkingAccount) {
        checkingAccounts.remove(checkingAccount);
    }
    public CheckingAccount read(String accountNumber) {
        if (!checkingAccounts.isEmpty()) {
            for (CheckingAccount c : checkingAccounts) {
                if (c.getAccountNumber().equals(accountNumber)) {
                    return c;
                }
            }
        }
        return null;
    }
}
