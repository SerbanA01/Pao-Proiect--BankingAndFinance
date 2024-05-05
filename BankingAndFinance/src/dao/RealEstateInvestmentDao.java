package dao;

import java.sql.*;
import model.investments.RealEstateInvestment;
import daoservices.DatabaseConnection;

public class RealEstateInvestmentDao implements DaoInterface<RealEstateInvestment> {
    private static RealEstateInvestmentDao realEstateInvestmentDao;
    private Connection connection = DatabaseConnection.getConnection();

    private RealEstateInvestmentDao() throws SQLException {}

    public static RealEstateInvestmentDao getInstance() throws SQLException {
        if (realEstateInvestmentDao == null) {
            realEstateInvestmentDao = new RealEstateInvestmentDao();
        }
        return realEstateInvestmentDao;
    }

    @Override
    public void add(RealEstateInvestment realEstateInvestment) throws SQLException {
        String sql = "INSERT INTO bankingdb.realestateinvestments  VALUES (?, ?, ?, ?, ?, ?);";
        String sql2 = "INSERT INTO bankingdb.investments  VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, realEstateInvestment.getInvestmentId());
            statement.setString(2, realEstateInvestment.getInvestmentName());
            statement.setDouble(3, realEstateInvestment.getInvestmentValue());
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, realEstateInvestment.getInvestmentId());
            statement.setDouble(2, realEstateInvestment.getSurface());
            statement.setDouble(3, realEstateInvestment.getPricePerSquareMeter());
            statement.setString(4, realEstateInvestment.getAddress());
            statement.setString(5, realEstateInvestment.getPropertyType());
            statement.setString(6, realEstateInvestment.getAnnualYield());
            statement.executeUpdate();
        }
    }

    @Override
    public RealEstateInvestment read(String investmentId) throws SQLException {
        String sql = "SELECT * FROM bankingdb.realestateinvestments s WHERE s.investmentId = ?";
        ResultSet rs = null;
        String sql2 = "SELECT * FROM bankingdb.investments s WHERE s.investmentId = ?";
        ResultSet rs2 = null;
        String investmentName = null;
        double investmentValue = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, investmentId);
            rs2 = statement.executeQuery();

            while (rs2.next()) {
                investmentName = rs2.getString("investmentName");
                investmentValue = rs2.getDouble("investmentValue");
            }
        } finally {
            if (rs2 != null) {
                rs2.close();
            }
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, investmentId);
            rs = statement.executeQuery();

            while (rs.next()) {
                RealEstateInvestment realEstateInvestment = new RealEstateInvestment();
                realEstateInvestment.setInvestmentId(rs.getString("investmentId"));
                realEstateInvestment.setSurface(rs.getDouble("surface"));
                realEstateInvestment.setInvestmentName(investmentName);
                realEstateInvestment.setInvestmentValue(investmentValue);
                realEstateInvestment.setPricePerSquareMeter(rs.getDouble("pricePerSquareMeter"));
                realEstateInvestment.setAddress(rs.getString("address"));
                realEstateInvestment.setPropertyType(rs.getString("propertyType"));
                realEstateInvestment.setAnnualYield(rs.getString("annualYield"));
                return realEstateInvestment;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(RealEstateInvestment realEstateInvestment) throws SQLException {
        String sql = "DELETE FROM bankingdb.realestateinvestments WHERE investmentId = ?";
        String sql2 = "DELETE FROM bankingdb.investments WHERE investmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, realEstateInvestment.getInvestmentId());
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, realEstateInvestment.getInvestmentId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(RealEstateInvestment realEstateInvestment) throws SQLException {
        String sql = "UPDATE bankingdb.realestateinvestments SET  surface = ?, pricePerSquareMeter = ?, address = ?, propertyType = ?, annualYield = ? WHERE investmentId = ?";
        String sql2 = "UPDATE bankingdb.investments SET investmentName = ?, investmentValue = ? WHERE investmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, realEstateInvestment.getInvestmentName());
            statement.setDouble(2, realEstateInvestment.getInvestmentValue());
            statement.setString(3, realEstateInvestment.getInvestmentId());
            statement.executeUpdate();
        }
        //in update remember that when you change them you put null in them because the function only receives
        //a reastateinvestment object not an investment object
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, realEstateInvestment.getSurface());
            statement.setDouble(2, realEstateInvestment.getPricePerSquareMeter());
            statement.setString(3, realEstateInvestment.getAddress());
            statement.setString(4, realEstateInvestment.getPropertyType());
            statement.setString(5, realEstateInvestment.getAnnualYield());
            statement.setString(6, realEstateInvestment.getInvestmentId());
            statement.executeUpdate();
        }
    }
}
