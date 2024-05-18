package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import daoservices.DatabaseConnection;
import model.Transaction;


public class TransactionDao implements DaoInterface<Transaction> {
    private static TransactionDao transactionDao;
    private Connection connection = DatabaseConnection.getConnection();

    public TransactionDao() throws SQLException {
    }

    public static TransactionDao getInstance() throws SQLException {
        if (transactionDao == null) {
            transactionDao = new TransactionDao();
        }
        return transactionDao;
    }

    @Override
    public void add(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO bankingdb.transactions VALUES (?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, transaction.getTransactionId());
            statement.setString(2, transaction.getReceiver());
            statement.setString(3, transaction.getSender());
            statement.setDouble(4, transaction.getAmount());
            statement.executeUpdate();
        }


    }
    public List<Transaction> getAllTransactionsByAccount(String accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM bankingdb.transactions WHERE sender = ? OR receiver = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId);
            statement.setString(2, accountId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String transactionId = rs.getString("transactionId");
                String receiver = rs.getString("receiver");
                String sender = rs.getString("sender");
                double amount = rs.getDouble("amount");
                transactions.add(new Transaction(transactionId, receiver, sender, amount));
            }
        }

        return transactions;
    }

    @Override
    public Transaction read(String transactionId) throws SQLException {
        String sql = "SELECT * FROM bankingdb.transactions s WHERE s.transactionId = ?";
        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, transactionId);
            rs = statement.executeQuery();

            while (rs.next()) {
                String receiver = rs.getString("receiver");
                String sender = rs.getString("sender");
                double amount = rs.getDouble("amount");
                return new Transaction(transactionId, receiver, sender, amount);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(Transaction transaction) throws SQLException {
        String sql = "UPDATE bankingdb.transactions SET receiver = ?, sender = ?, amount = ? WHERE transactionId = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, transaction.getReceiver());
            statement.setString(2, transaction.getSender());
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getTransactionId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Transaction transaction) throws SQLException {
        String sql = "DELETE FROM bankingdb.transactions WHERE transactionId = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, transaction.getTransactionId());
            statement.executeUpdate();
        }
    }



}
