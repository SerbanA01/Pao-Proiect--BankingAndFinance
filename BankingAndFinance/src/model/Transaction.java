package model;

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


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
