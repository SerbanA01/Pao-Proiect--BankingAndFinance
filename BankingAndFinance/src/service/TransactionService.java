package service;

import daoservices.TransactionsRepoService;
import model.Transaction;
import model.accounts.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TransactionService {

    private TransactionsRepoService dbService;
    private AccountsService accountsService;

    public TransactionService() throws SQLException {
        this.dbService = new TransactionsRepoService();
    }

    public void setAccountsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    public void create(Scanner scanner, String senderId) {
        try {
            transactionInit(scanner, senderId);
        } catch (SQLException e) {
            System.out.println("Error creating transaction");
        }
    }

    public void read(Scanner scanner) {
        System.out.println("Enter transaction id: ");
        String transactionId = scanner.nextLine();
        try {
            dbService.getTransactionById(transactionId);
        } catch (SQLException e) {
            System.out.println("Error reading transaction");
        }
    }

    public void delete(Scanner scanner) {
        System.out.println("Enter transaction id: ");
        String transactionId = scanner.nextLine();
        try {
            dbService.removeTransaction(transactionId);
        } catch (SQLException e) {
            System.out.println("Error deleting transaction");
        }
    }

    public void readAll(String accountNumber) {
        try {
            List<Transaction> transactions = dbService.getAllTransactions(accountNumber);
            for (Transaction transaction : transactions) {
                if ( transaction.getReceiver().equals(accountNumber)){
                    System.out.println("You received " + transaction.getAmount() + " from " + transaction.getSender());
                }
                if ( transaction.getSender().equals(accountNumber)){
                    System.out.println("You sent " + transaction.getAmount() + " to " + transaction.getReceiver());
                }
            }
            System.out.println("Do you want to see the transactions sorted by amount? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                transactions.sort((t1, t2) -> (int) (t1.getAmount() - t2.getAmount()));
                for (Transaction transaction : transactions) {
                    System.out.println("Transaction id: " + transaction.getTransactionId() + " Receiver: " + transaction.getReceiver() + " Sender: " + transaction.getSender() + " Amount: " + transaction.getAmount());

                }
            }
        } catch (SQLException e) {
            System.out.println("Error reading all transactions");
        }
    }
    public void transactionInit(Scanner scanner, String senderId) throws SQLException {
        System.out.println("Enter receiver id: ");
        String receiverId = scanner.nextLine();
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        //we get the account for the sender and check if the amount is available
        Account senderAccount = accountsService.getAccountById(senderId);
        Account receiverAccount = accountsService.getAccountById(receiverId);
        if (amount > senderAccount.getBalance()) {
            System.out.println("Insufficient funds");
            return;
        }


        long transactionId = (long) (Math.random() * 1000000000);
        String transactionIdString = String.valueOf(transactionId);
        scanner.nextLine();
        Transaction transaction = new Transaction(transactionIdString, receiverId, senderId, amount);

        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);

        //update the balances
        accountsService.updateAccount(senderAccount);
        accountsService.updateAccount(receiverAccount);


        try {
            dbService.addTransaction(transaction);
        } catch (SQLException e) {
            System.out.println("Error creating transaction" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
