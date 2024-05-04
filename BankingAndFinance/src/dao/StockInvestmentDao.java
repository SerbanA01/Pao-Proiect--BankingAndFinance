package dao;

import java.sql.*;
import model.investments.StockInvestment;
import daoservices.DatabaseConnection;

public class StockInvestmentDao implements DaoInterface<StockInvestment> {
    private static StockInvestmentDao stockInvestmentDao;
    private Connection connection = DatabaseConnection.getConnection();

    private StockInvestmentDao() throws SQLException {}

    public static StockInvestmentDao getInstance() throws SQLException {
        if (stockInvestmentDao == null) {
            stockInvestmentDao = new StockInvestmentDao();
        }
        return stockInvestmentDao;
    }

    @Override
    public void add(StockInvestment stockInvestment) throws SQLException {
        String sql = "INSERT INTO bankingdb.stockinvestments  VALUES (?, ?, ?, ?);";
        String sql2 = "INSERT INTO bankingdb.investments  VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, stockInvestment.getInvestmentId());
            statement.setString(2, stockInvestment.getInvestmentName());
            statement.setDouble(3, stockInvestment.getInvestmentValue());
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, stockInvestment.getInvestmentId());
            statement.setInt(2, stockInvestment.getNumberOfStocks());
            statement.setDouble(3, stockInvestment.getStockPrice());
            statement.setString(4, stockInvestment.getStockSymbol());
            statement.executeUpdate();
        }
    }

    @Override
    public StockInvestment read(String investmentId) throws SQLException {
        String sql = "SELECT * FROM bankingdb.stockinvestment s WHERE s.investmentId = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, investmentId);
            rs = statement.executeQuery();

            while (rs.next()) {
                StockInvestment stockInvestment = new StockInvestment();
                stockInvestment.setInvestmentId(rs.getString("investmentId"));
                stockInvestment.setInvestmentName(rs.getString("investmentName"));
                stockInvestment.setInvestmentValue(rs.getDouble("investmentValue"));
                stockInvestment.setNumberOfStocks(rs.getInt("numberOfStocks"));
                stockInvestment.setStockPrice(rs.getDouble("stockPrice"));
                stockInvestment.setStockSymbol(rs.getString("stockSymbol"));
                return stockInvestment;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(StockInvestment stockInvestment) throws SQLException {
        String sql = "DELETE FROM bankingdb.stockinvestment WHERE investmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, stockInvestment.getInvestmentId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(StockInvestment stockInvestment) throws SQLException {
        String sql = "UPDATE bankingdb.stockinvestment SET investmentName = ?, investmentValue = ?, numberOfStocks = ?, stockPrice = ?, stockSymbol = ? WHERE investmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, stockInvestment.getInvestmentName());
            statement.setDouble(2, stockInvestment.getInvestmentValue());
            statement.setInt(3, stockInvestment.getNumberOfStocks());
            statement.setDouble(4, stockInvestment.getStockPrice());
            statement.setString(5, stockInvestment.getStockSymbol());
            statement.setString(6, stockInvestment.getInvestmentId());
            statement.executeUpdate();
        }
    }
}
