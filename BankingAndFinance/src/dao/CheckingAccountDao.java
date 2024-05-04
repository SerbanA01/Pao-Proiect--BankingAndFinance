package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import daoservices.DatabaseConnection;
import model.accounts.CheckingAccount;

public class CheckingAccountDao implements DaoInterface<CheckingAccount> {

    private static CheckingAccountDao checkingAccountDao;

    private Connection connection = DatabaseConnection.getConnection();

    public CheckingAccountDao() throws SQLException {
    }

    public static CheckingAccountDao getInstance() throws SQLException {
        if (checkingAccountDao == null) {
            checkingAccountDao = new CheckingAccountDao();
        }
        return checkingAccountDao;
    }

    @Override
    public void add(CheckingAccount checkingAccount) throws SQLException {
        String sql = "INSERT INTO bankingdb.checkingaccounts VALUES (?, ?, ?, ?);";
        String sql2 = "INSERT INTO bankingdb.accounts VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, checkingAccount.getAccountNumber());
            statement.setString(2, checkingAccount.getAccountHolder());
            statement.setDouble(3, checkingAccount.getBalance());
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, checkingAccount.getAccountNumber());
            statement.setDouble(2, checkingAccount.getOverdraftLimit());
            statement.setDouble(3, checkingAccount.getTransactionFee());
            statement.setString(4, checkingAccount.getDebitCardNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public CheckingAccount read(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM bankingdb.checkingaccount s WHERE s.accountNumber = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, accountNumber);
            rs = statement.executeQuery();

            while (rs.next()) {
                CheckingAccount checkingAccount = new CheckingAccount(rs.getString("accountNumber"), rs.getString("accountHolder"), rs.getDouble("balance"), rs.getDouble("overdraftLimit"), rs.getDouble("transactionFee"), rs.getString("debitCardNumber"));
                return checkingAccount;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(CheckingAccount checkingAccount) throws SQLException {
        String sql = "DELETE FROM bankingdb.checkingaccount WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, checkingAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(CheckingAccount checkingAccount) throws SQLException {
        String sql = "UPDATE bankingdb.checkingaccount SET balance = ?, overdraftLimit = ?, transactionFee = ?, debitCardNumber = ? WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setDouble(1, checkingAccount.getBalance());
            statement.setDouble(2, checkingAccount.getOverdraftLimit());
            statement.setDouble(3, checkingAccount.getTransactionFee());
            statement.setString(4, checkingAccount.getDebitCardNumber());
            statement.setString(5, checkingAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }
}
