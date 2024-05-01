package dao;

import java.util.List;
import java.util.ArrayList;
import model.accounts.SavingsAccount;

public class SavingsAccountDao {
    private static List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
    public void create(SavingsAccount savingsAccount) {
        savingsAccounts.add(savingsAccount);
    }
    public void delete(SavingsAccount savingsAccount) {
        savingsAccounts.remove(savingsAccount);
    }
    public SavingsAccount read(String accountNumber) {
        if (!savingsAccounts.isEmpty()) {
            for (SavingsAccount s : savingsAccounts) {
                if (s.getAccountNumber().equals(accountNumber)) {
                    return s;
                }
            }
        }
        return null;
    }
}
