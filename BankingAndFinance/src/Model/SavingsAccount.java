package Model;

public class SavingsAccount extends Account{
    private double interestRate;
    private double minimumBalance;
    private double penalty;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate, double minimumBalance, double penalty) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.penalty = penalty;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "interestRate=" + interestRate +
                ", minimumBalance=" + minimumBalance +
                ", penalty=" + penalty +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                '}';
    }
}
