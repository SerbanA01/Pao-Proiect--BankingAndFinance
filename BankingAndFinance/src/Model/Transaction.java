package Model;

public class Transaction {
    private String transactionId;
    private String receiver;
    private String sender;
    private double amount;

    public Transaction(String transactionId, String receiver, String sender, double amount) {
        this.transactionId = transactionId;
        this.receiver = receiver;
        this.sender = sender;
        this.amount = amount;
    }


}
