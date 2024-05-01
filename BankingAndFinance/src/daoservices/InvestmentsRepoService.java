package daoservices;

import model.investments.Investment;
import model.investments.RealEstateInvestment;
import model.investments.StockInvestment;
import dao.RealEstateInvestmentDao;
import dao.StockInvestmentDao;

public class InvestmentsRepoService {

    private RealEstateInvestmentDao realEstateInvestmentDao;
    private StockInvestmentDao stockInvestmentDao;

    public InvestmentsRepoService() {
        this.realEstateInvestmentDao = new RealEstateInvestmentDao();
        this.stockInvestmentDao = new StockInvestmentDao();
    }

    public RealEstateInvestment getRealEstateInvestmentById(String id) {
        RealEstateInvestment realEstateInvestment = realEstateInvestmentDao.read(id);
        if(realEstateInvestment == null) {
            System.out.println("Real Estate Investment not found");
            return null;
        }
        System.out.println("Real Estate Investment found: " + realEstateInvestment.getInvestmentId() + " " + realEstateInvestment.getInvestmentName());
        return realEstateInvestment;
    }

    public StockInvestment getStockInvestmentById(String id) {
        StockInvestment stockInvestment = stockInvestmentDao.read(id);
        if(stockInvestment == null) {
            System.out.println("Stock Investment not found");
            return null;
        }
        System.out.println("Stock Investment found: " + stockInvestment.getInvestmentId() + " " + stockInvestment.getInvestmentName());
        return stockInvestment;
    }

    public void removeInvestment(String investmentType, String id) {

        Investment investment = getInvestment(investmentType, id);
        if(investment == null) {
            System.out.println("Investment not found");
            return;
        }

        switch (investment) {
            case RealEstateInvestment realEstateInvestment -> realEstateInvestmentDao.delete(realEstateInvestment);
            case StockInvestment stockInvestment -> stockInvestmentDao.delete(stockInvestment);
            default -> throw new IllegalStateException("Unexpected value: " + investment);
        }
    }

    public Investment getInvestment(String investmentType, String id) {
        Investment investment = null;
        switch (investmentType) {
            case "realestate" -> investment = getRealEstateInvestmentById(id);
            case "stock" -> investment = getStockInvestmentById(id);
            default -> System.out.println("Invalid investment type");
        }
        return investment;
    }

    public void addInvestment(Investment investment) {
        if(investment == null) {
            System.out.println("Investment not found");
            return;
        }
        switch (investment) {
            case RealEstateInvestment realEstateInvestment -> realEstateInvestmentDao.create(realEstateInvestment);
            case StockInvestment stockInvestment -> stockInvestmentDao.create(stockInvestment);
            default -> throw new IllegalStateException("Unexpected value: " + investment);
        }
        System.out.println("Added " + investment.getInvestmentId() + " " + investment.getInvestmentName() + " to the database");
    }


}
