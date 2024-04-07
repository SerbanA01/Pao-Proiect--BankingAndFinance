package service;

import daoservices.InvestmentsRepoService;
import model.Investments.Investment;
import model.Investments.RealEstateInvestment;
import model.Investments.StockInvestment;
import java.util.Scanner;

public class InvestmentsService {
    private InvestmentsRepoService dbservice;

    public InvestmentsService() {
        this.dbservice = new InvestmentsRepoService();
    }

    public void create(Scanner scanner) {
        System.out.println("Enter investment type [realEstate/stock]:");
        String investmentType = scanner.nextLine().toLowerCase();
        if(!investmentType.equals("realestate") && !investmentType.equals("stock") ) { return; }
        investmentInit(scanner, investmentType);

    }

    public void read(Scanner scanner) {
        System.out.println("Enter investment ID:");
        String id = scanner.nextLine();
        dbservice.getStockInvestmentById(id);
        dbservice.getRealEstateInvestmentById(id);

    }

    public void delete(Scanner scanner) {
        System.out.println("Enter investment type [realEstate/stock]:");
        String investmentType = scanner.nextLine().toLowerCase();
        System.out.println("Enter investment ID:");
        String id = scanner.nextLine();

        if(!investmentType.equals("realestate") && !investmentType.equals("stock") ) { return; }
        dbservice.removeInvestment(investmentType, id);

    }

    public void update(Scanner scanner) {
        System.out.println("Enter investment type [realEstate/stock]:");
        String investmentType = scanner.nextLine().toLowerCase();
        if(!investmentType.equals("realestate") && !investmentType.equals("stock") ) { return; }
        System.out.println("Enter investment ID:");
        String id = scanner.nextLine();
        System.out.println("Enter the new investment name:");
        String investmentName = scanner.nextLine();
        System.out.println("Enter the new investment value:");
        double investmentValue = scanner.nextDouble();

        Investment investment = dbservice.getInvestment(investmentType, id);
        if(investment == null) { return; }
        investment.setInvestmentName(investmentName);
        investment.setInvestmentValue(investmentValue);
        if(investmentType.equals("realestate")) {
            realEstateInit(scanner, (RealEstateInvestment) investment);
        } else if(investmentType.equals("stock")) {
            stockInit(scanner, (StockInvestment) investment);
        }


    }

    /*
    *     private String investmentId;
    private String investmentName;
    private double investmentValue;
    *
    * */
    private void investmentInit(Scanner scanner, String investmentType) {

        long investmentId = (long) (Math.random() * 10000000000000000L);
        String investmentIdString = String.valueOf(investmentId);
        System.out.println("Enter the investment name:");
        String investmentName = scanner.nextLine();
        System.out.println("Enter the investment value:");
        double investmentValue = scanner.nextDouble();


        Investment investment = new Investment(investmentIdString, investmentName, investmentValue);
        if(investmentType.equals("realestate")) {
              RealEstateInvestment realEstateInvestment = new RealEstateInvestment(investment);
              realEstateInit(scanner, realEstateInvestment);
              investment = realEstateInvestment;
        } else if(investmentType.equals("stock")) {
                StockInvestment stockInvestment = new StockInvestment(investment);
                stockInit(scanner, stockInvestment);
                investment = stockInvestment;

        }

        dbservice.addInvestment(investment);
        System.out.println("Investment added successfully! Investment ID: " + investmentIdString + " Investment Name: " + investmentName + " Investment Value: " + investmentValue + " Investment Type: " + investmentType );


    }

    private void realEstateInit(Scanner scanner, RealEstateInvestment realEstateInvestment) {
        System.out.println("Enter the surface area:");
        double surface = scanner.nextDouble();
        System.out.println("Enter the price per square meter:");
        double pricePerSquareMeter = scanner.nextDouble();
        scanner.nextLine(); // consume the newline left-over
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
        System.out.println("Enter the stock price:");
        double stockPrice = scanner.nextDouble();
        scanner.nextLine(); // consume the newline left-over to capture the next line of input correctly
        System.out.println("Enter the stock symbol:");
        String stockSymbol = scanner.nextLine();

        stockInvestment.setNumberOfStocks(numberOfStocks);
        stockInvestment.setStockPrice(stockPrice);
        stockInvestment.setStockSymbol(stockSymbol);
    }




}
