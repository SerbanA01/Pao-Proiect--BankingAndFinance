package service;

import daoservices.InvestmentsRepoService;
import model.investments.Investment;
import model.investments.RealEstateInvestment;
import model.investments.StockInvestment;
import others.AuditManagement;

import java.sql.SQLException;
import java.util.Scanner;

public class InvestmentsService {
    private InvestmentsRepoService dbservice;

    public InvestmentsService() throws SQLException {
        this.dbservice = new InvestmentsRepoService();
    }



    public void create(Scanner scanner) {
        System.out.println("Enter investment type [RealEstate/Stock]:");
        String investmentType = scanner.nextLine().toLowerCase();
        if(!investmentType.equals("realestate") && !investmentType.equals("stock") ) { return; }
        try {
            investmentInit(scanner, investmentType);
        }
        catch (SQLException e) {
            System.out.println("Investment could not be created " + e.getSQLState() + " " + e.getMessage());
        }

    }

    public void read(Scanner scanner) {
        System.out.println("Enter investment ID:");
        String id = scanner.nextLine();
        try{
        dbservice.getStockInvestmentById(id);
        AuditManagement.writeToFile("Read stock investment " + id);
        }
        catch (SQLException e) {
            System.out.println("Investment could not be read " + e.getSQLState() + " " + e.getMessage());
        }
        try {
            dbservice.getRealEstateInvestmentById(id);
            AuditManagement.writeToFile("Read real estate investment " + id);
        }
        catch (SQLException e) {
            System.out.println("Investment could not be read " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void delete(Scanner scanner) {
        System.out.println("Enter investment type [RealEstate/Stock]:");
        String investmentType = scanner.nextLine().toLowerCase();
        System.out.println("Enter investment ID:");
        String id = scanner.nextLine();

        if(!investmentType.equals("realestate") && !investmentType.equals("stock") ) { return; }
        try {
            dbservice.removeInvestment(investmentType, id);
            AuditManagement.writeToFile("Deleted investment " + investmentType + " " + id);
        }
        catch (SQLException e) {
            System.out.println("Investment could not be deleted " + e.getSQLState() + " " + e.getMessage());
        }

    }

    public void update(Scanner scanner) {
        System.out.println("Enter investment type [RealEstate/Stock]:");
        String investmentType = scanner.nextLine().toLowerCase();
        if(!investmentType.equals("realestate") && !investmentType.equals("stock") ) { return; }
        System.out.println("Enter investment ID:");
        String id = scanner.nextLine();

        Investment investment = dbservice.getInvestment(investmentType, id);
        if(investment == null) { return; }

        System.out.println("Enter the new investment name:");
        String investmentName = scanner.nextLine();
        System.out.println("Enter the new investment value:");
        double investmentValue = scanner.nextDouble();
        scanner.nextLine();




        investment.setInvestmentName(investmentName);
        investment.setInvestmentValue(investmentValue);
        if(investmentType.equals("realestate")) {
            realEstateInit(scanner, (RealEstateInvestment) investment);
        } else if(investmentType.equals("stock")) {
            stockInit(scanner, (StockInvestment) investment);
        }

        try {
            dbservice.updateInvestment(investment);
            AuditManagement.writeToFile("Updated investment " + investment);
        }
        catch (SQLException e) {
            System.out.println("Investment could not be updated " + e.getSQLState() + " " + e.getMessage());
        }

    }

    /*
    *     private String investmentId;
    private String investmentName;
    private double investmentValue;
    *
    * */
    private void investmentInit(Scanner scanner, String investmentType) throws SQLException {

        long investmentId = (long) (Math.random() * 10000000000000000L);
        String investmentIdString = String.valueOf(investmentId);
        System.out.println("Enter the investment name:");
        String investmentName = scanner.nextLine();
        System.out.println("Enter the investment value:");
        double investmentValue = scanner.nextDouble();
        scanner.nextLine();


        Investment investment = new Investment(investmentIdString, investmentName, investmentValue);
        AuditManagement.writeToFile("Created investment" + investment);

        if(investmentType.equals("realestate")) {
              RealEstateInvestment realEstateInvestment = new RealEstateInvestment(investment);
              realEstateInit(scanner, realEstateInvestment);
              investment = realEstateInvestment;
        } else if(investmentType.equals("stock")) {
                StockInvestment stockInvestment = new StockInvestment(investment);
                stockInit(scanner, stockInvestment);
                investment = stockInvestment;

        }

        try {
            dbservice.addInvestment(investment);
            System.out.println("Investment added successfully! Investment ID: " + investmentIdString + " Investment Name: " + investmentName + " Investment Value: " + investmentValue + " Investment Type: " + investmentType);
        }
        catch (SQLException e) {
            System.out.println("Investment could not be added " + e.getSQLState() + " " + e.getMessage());
        }

    }

    private void realEstateInit(Scanner scanner, RealEstateInvestment realEstateInvestment) {
        System.out.println("Enter the surface area:");
        double surface = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the price per square meter:");
        double pricePerSquareMeter = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        System.out.println("Enter the property type:");
        String propertyType = scanner.nextLine();
        System.out.println("Enter the annual yield:");
        String annualYield = scanner.nextLine();

        realEstateInvestment.setSurface(surface);
        realEstateInvestment.setPricePerSquareMeter(pricePerSquareMeter);
        realEstateInvestment.setAddress(address);
        realEstateInvestment.setPropertyType(propertyType);
        realEstateInvestment.setAnnualYield(annualYield);




    }
    private void stockInit(Scanner scanner, StockInvestment stockInvestment) {
        System.out.println("Enter the number of stocks:");
        int numberOfStocks = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the stock price:");
        double stockPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the stock symbol:");
        String stockSymbol = scanner.nextLine();

        stockInvestment.setNumberOfStocks(numberOfStocks);
        stockInvestment.setStockPrice(stockPrice);
        stockInvestment.setStockSymbol(stockSymbol);
    }




}
