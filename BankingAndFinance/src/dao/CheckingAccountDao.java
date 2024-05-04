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
        String sql2 = "SELECT * FROM bankingdb.account s WHERE s.accountNumber = ?";
        ResultSet rs = null;
        ResultSet rs2 = null;
        String accountHolder = null;
        double balance = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, accountNumber);
            rs2 = statement.executeQuery();

            while (rs2.next()) {
                accountHolder = rs2.getString("accountHolder");
                balance = rs2.getDouble("balance");
            }
        } finally {
            if (rs2 != null) {
                rs2.close();
            }
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, accountNumber);
            rs = statement.executeQuery();

            while (rs.next()) {
                CheckingAccount checkingAccount = new CheckingAccount();
                checkingAccount.setAccountNumber(accountNumber);
                checkingAccount.setAccountHolder(accountHolder);
                checkingAccount.setBalance(balance);
                checkingAccount.setOverdraftLimit(rs.getDouble("overdraftLimit"));
                checkingAccount.setTransactionFee(rs.getDouble("transactionFee"));
                checkingAccount.setDebitCardNumber(rs.getString("debitCardNumber"));
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
        String sql2 = "DELETE FROM bankingdb.account WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, checkingAccount.getAccountNumber());
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, checkingAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(CheckingAccount checkingAccount) throws SQLException {
        String sql = "UPDATE bankingdb.checkingaccount SET balance = ?, overdraftLimit = ?, transactionFee = ?, debitCardNumber = ? WHERE accountNumber = ?";
        String sql2 = "UPDATE bankingdb.account SET accountHolder = ?, balance = ? WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, checkingAccount.getAccountHolder());
            statement.setDouble(2, checkingAccount.getBalance());
            statement.setString(3, checkingAccount.getAccountNumber());
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setDouble(1, checkingAccount.getOverdraftLimit());
            statement.setDouble(2, checkingAccount.getTransactionFee());
            statement.setString(3, checkingAccount.getDebitCardNumber());
            statement.setString(4, checkingAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }
}
