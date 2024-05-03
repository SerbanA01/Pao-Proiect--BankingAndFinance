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
        String sql = "INSERT INTO proiectpao.realestateinvestment (investmentId, investmentName, investmentValue, surface, pricePerSquareMeter, address, propertyType, annualYield) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, realEstateInvestment.getInvestmentId());
            statement.setString(2, realEstateInvestment.getInvestmentName());
            statement.setDouble(3, realEstateInvestment.getInvestmentValue());
            statement.setDouble(4, realEstateInvestment.getSurface());
            statement.setDouble(5, realEstateInvestment.getPricePerSquareMeter());
            statement.setString(6, realEstateInvestment.getAddress());
            statement.setString(7, realEstateInvestment.getPropertyType());
            statement.setString(8, realEstateInvestment.getAnnualYield());
            statement.executeUpdate();
        }
    }

    @Override
    public RealEstateInvestment read(String investmentId) throws SQLException {
        String sql = "SELECT * FROM proiectpao.realestateinvestment s WHERE s.investmentId = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, investmentId);
            rs = statement.executeQuery();

            while (rs.next()) {
                RealEstateInvestment realEstateInvestment = new RealEstateInvestment();
                realEstateInvestment.setInvestmentId(rs.getString("investmentId"));
                realEstateInvestment.setInvestmentName(rs.getString("investmentName"));
                realEstateInvestment.setInvestmentValue(rs.getDouble("investmentValue"));
                realEstateInvestment.setSurface(rs.getDouble("surface"));
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
        String sql = "DELETE FROM proiectpao.realestateinvestment WHERE investmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, realEstateInvestment.getInvestmentId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(RealEstateInvestment realEstateInvestment) throws SQLException {
        String sql = "UPDATE proiectpao.realestateinvestment SET investmentName = ?, investmentValue = ?, surface = ?, pricePerSquareMeter = ?, address = ?, propertyType = ?, annualYield = ? WHERE investmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, realEstateInvestment.getInvestmentName());
            statement.setDouble(2, realEstateInvestment.getInvestmentValue());
            statement.setDouble(3, realEstateInvestment.getSurface());
            statement.setDouble(4, realEstateInvestment.getPricePerSquareMeter());
            statement.setString(5, realEstateInvestment.getAddress());
            statement.setString(6, realEstateInvestment.getPropertyType());
            statement.setString(7, realEstateInvestment.getAnnualYield());
            statement.setString(8, realEstateInvestment.getInvestmentId());
            statement.executeUpdate();
        }
    }
}
