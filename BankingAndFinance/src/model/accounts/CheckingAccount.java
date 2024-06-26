package model.accounts;

public class CheckingAccount extends Account  {
    private double overdraftLimit;
    private double transactionFee;
    private String debitCardNumber;

    public CheckingAccount() {
    }
    public CheckingAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit, double transactionFee, String debitCardNumber) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
        this.transactionFee = transactionFee;
        this.debitCardNumber = debitCardNumber;
    }

    public CheckingAccount(Account account){
        super(account.getAccountNumber(), account.getAccountHolder(), account.getBalance());
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public String getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public void setDebitCardNumber(String debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "overdraftLimit=" + overdraftLimit +
                ", transactionFee=" + transactionFee +
                ", debitCardNumber='" + debitCardNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                '}';
    }



}
