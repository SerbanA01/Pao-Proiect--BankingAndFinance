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
        String sql = "SELECT * FROM bankingdb.savingsaccount s WHERE s.accountNumber = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNumber);
            rs = statement.executeQuery();

            while (rs.next()) {
                SavingsAccount savingsAccount = new SavingsAccount();
                savingsAccount.setAccountNumber(rs.getString("accountNumber"));
                savingsAccount.setAccountHolder(rs.getString("accountHolder"));
                savingsAccount.setBalance(rs.getDouble("balance"));
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
        String sql = "DELETE FROM bankingdb.savingsaccount WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, savingsAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(SavingsAccount savingsAccount) throws SQLException {
        String sql = "UPDATE bankingdb.savingsaccount SET accountHolder = ?, balance = ?, interestRate = ?, minimumBalance = ?, penalty = ? WHERE accountNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, savingsAccount.getAccountHolder());
            statement.setDouble(2, savingsAccount.getBalance());
            statement.setDouble(3, savingsAccount.getInterestRate());
            statement.setDouble(4, savingsAccount.getMinimumBalance());
            statement.setDouble(5, savingsAccount.getPenalty());
            statement.setString(6, savingsAccount.getAccountNumber());
            statement.executeUpdate();
        }
    }
}
