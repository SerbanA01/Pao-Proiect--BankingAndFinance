package daoservices;

import model.investments.Investment;
import dao.StockInvestmentDao;
import dao.RealEstateInvestmentDao;
import model.investments.StockInvestment;
import model.investments.RealEstateInvestment;
import java.sql.SQLException;



public class InvestmentsRepoService {

    private StockInvestmentDao stockInvestmentDao = StockInvestmentDao.getInstance();
    private RealEstateInvestmentDao realEstateInvestmentDao = RealEstateInvestmentDao.getInstance();

    public InvestmentsRepoService() throws SQLException {}

    public RealEstateInvestment getRealEstateInvestmentById(String investmentId) throws SQLException {
        RealEstateInvestment realEstateInvestment = realEstateInvestmentDao.read(investmentId);
        if(realEstateInvestment != null){
            System.out.println(realEstateInvestment);
        }else {
            System.out.println("No real estate investment found with this ID");
        }

        return realEstateInvestment;
    }

    public StockInvestment getStockInvestmentById(String investmentId) throws SQLException {
        StockInvestment stockInvestment = stockInvestmentDao.read(investmentId);
        if(stockInvestment != null){
            System.out.println(stockInvestment);
        }else {
            System.out.println("No stock investment found with this ID");
        }
        return stockInvestment;
    }

    public void removeInvestment(String typeOfInvestment, String investmentId) throws SQLException {
        Investment investment = getInvestment(typeOfInvestment, investmentId);
        if (investment == null) return;

        switch (investment){
            case RealEstateInvestment realEstateInvestment -> realEstateInvestmentDao.delete(realEstateInvestment);
            case StockInvestment stockInvestment -> stockInvestmentDao.delete(stockInvestment);
            default -> throw new IllegalStateException("Unexpected value: " + investment);
        }

        System.out.println("Removed " + investment);
    }

    public void addInvestment(Investment investment) throws SQLException {
        if(investment != null){
            switch (investment){
                case RealEstateInvestment realEstateInvestment -> realEstateInvestmentDao.add(realEstateInvestment);
                case StockInvestment stockInvestment -> stockInvestmentDao.add(stockInvestment);
                default -> throw new IllegalStateException("Unexpected value: " + investment);
            }
        }
    }

    public Investment getInvestment(String typeOfInvestment, String investmentId) {
        Investment investment = null;
        try {
            if(typeOfInvestment.equals("realEstate")){
                investment = getRealEstateInvestmentById(investmentId);
            }else{
                investment = getStockInvestmentById(investmentId);
            }
            if(investment == null) {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }

        return investment;
    }

    public void updateInvestment(Investment investment) throws SQLException {
        if(investment != null){
            switch (investment){
                case RealEstateInvestment realEstateInvestment -> realEstateInvestmentDao.update(realEstateInvestment);
                case StockInvestment stockInvestment -> stockInvestmentDao.update(stockInvestment);
                default -> throw new IllegalStateException("Unexpected value: " + investment);
            }
        }
    }
}
