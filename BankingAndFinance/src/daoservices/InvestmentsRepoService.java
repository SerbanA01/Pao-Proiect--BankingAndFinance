package daoservices;

import model.Investments.Investment;
import model.Investments.RealEstateInvestment;
import model.Investments.StockInvestment;
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
        System.out.println("Real Estate Investment found: " + realEstateInvestment);
        return realEstateInvestment;
    }

    public StockInvestment getStockInvestmentById(String id) {
        StockInvestment stockInvestment = stockInvestmentDao.read(id);
        if(stockInvestment == null) {
            System.out.println("Stock Investment not found");
            return null;
        }
        System.out.println("Stock Investment found: " + stockInvestment);
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

    private Investment getInvestment(String investmentType, String id) {
        Investment investment = null;
        switch (investmentType) {
            case "RealEstateInvestment" -> investment = getRealEstateInvestmentById(id);
            case "StockInvestment" -> investment = getStockInvestmentById(id);
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
        System.out.println("Added " + investment);
    }


}
