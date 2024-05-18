package daoservices;

import dao.TransactionDao;
import model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionsRepoService {

    private TransactionDao transactionDao = TransactionDao.getInstance();

    public TransactionsRepoService() throws SQLException {}

    public Transaction getTransactionById(String transactionId) throws SQLException {
        Transaction transaction = transactionDao.read(transactionId);
        if(transaction != null){
            System.out.println(transaction);
        }else {
            System.out.println("No transaction with this id");
        }
        return transaction;
    }

    public void addTransaction(Transaction transaction) throws SQLException {
        if(transaction != null){
            transactionDao.add(transaction);
        }
    }

    public List<Transaction> getAllTransactions(String accountNumber) throws SQLException {
        List<Transaction> transactions = transactionDao.getAllTransactionsByAccount(accountNumber);
        if(transactions != null){
            System.out.println("Transactions retrieved successfully");
        }else {
            System.out.println("No transactions for this account");
        }
        return transactions;
    }


    public void removeTransaction(String transactionId) throws SQLException {
        Transaction transaction = getTransactionById(transactionId);
        if (transaction == null) return;
        transactionDao.delete(transaction);
        System.out.println("Removed " + transaction);
    }

}
