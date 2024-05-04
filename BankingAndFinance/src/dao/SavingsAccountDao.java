package dao;

import java.sql.*;
import model.accounts.SavingsAccount;
import daoservices.DatabaseConnection;

public class SavingsAccountDao implements DaoInterface<SavingsAccount> {
    private static SavingsAccountDao savingsAccountDao;
    private Connection connection = DatabaseConnection.getConnection();

    private SavingsAccountDao() throws SQLException {}

    public static SavingsAccountDao getInstance() throws SQLException {
        if (savingsAccountDao == null) {
            savingsAccountDao = new SavingsAccountDao();
        }
        return savingsAccountDao;
    }

    @Override
    public void add(SavingsAccount savingsAccount) throws SQLException {
        String sql = "INSERT INTO bankingdb.savingsaccounts  VALUES (?, ?, ?, ?);";
        String sql2 = "INSERT INTO bankingdb.accounts  VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, savingsAccount.getAccountNumber());
            statement.setString(2, savingsAccount.getAccountHolder());
            statement.setDouble(3, savingsAccount.getBalance());
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, savingsAccount.getAccountNumber());
            statement.setDouble(2, savingsAccount.getInterestRate());
            statement.setDouble(3, savingsAccount.getMinimumBalance());
            statement.setDouble(4, savingsAccount.getPenalty());
            statement.executeUpdate();
        }
    }

    @Override
    public SavingsAccount read(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM bankingdb.savingsaccounts s WHERE s.accountNumber = ?";
        ResultSet rs = null;
        String sql2 = "SELECT * FROM bankingdb.accounts s WHERE s.accountNumber = ?";
        ResultSet rs2 = null;
        String accountHolder = null;
        double balance = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
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


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNumber);
            rs = statement.executeQuery();

            while (rs.next()) {
                SavingsAccount savingsAccount = new SavingsAccount();
                savingsAccount.setAccountNumber(rs.getString("accountNumber"));
                savingsAccount.setAccountHolder(accountHolder);
                savingsAccount.setBalance(balance);
                savingsAccount.setInterestRate(rs.getDouble("interestRate"));
                savingsAccount.setMinimumBalance(rs.getDouble("minimumBalance"));
                savingsAccount.setPenalty(rs.getDouble("penalty"));
                return savingsAccount;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(SavingsAccount savingsAccount) throws SQLException {
        String sql = "DELETE FROM bankingdb.savingsaccounts WHERE accountNumber = ?";
        String sql2 = "DELETE FROM bankingdb.accounts WHERE accountNumber = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, savingsAccount.getAccountNumber());
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, savingsAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(SavingsAccount savingsAccount) throws SQLException {
        String sql = "UPDATE bankingdb.savingsaccounts SET interestRate = ?, minimumBalance = ?, penalty = ? WHERE accountNumber = ?";
        String sql2 = "UPDATE bankingdb.accounts SET accountHolder = ?, balance = ? WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, savingsAccount.getAccountHolder());
            statement.setDouble(2, savingsAccount.getBalance());
            statement.setString(3, savingsAccount.getAccountNumber());
            //think about the fact whether you want to have a getter for the account number called here
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, savingsAccount.getInterestRate());
            statement.setDouble(2, savingsAccount.getMinimumBalance());
            statement.setDouble(3, savingsAccount.getPenalty());
            //here aswell
            statement.setString(4, savingsAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }
}
