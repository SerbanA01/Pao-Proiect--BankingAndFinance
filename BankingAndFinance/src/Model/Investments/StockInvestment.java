package Model.Investments;

import Model.Investments.Investment;

public class StockInvestment extends Investment {

    private int numberOfStocks;
    private double stockPrice;
    private String stockSymbol;

    public StockInvestment(String investmentId, String investmentName, double investmentValue, int numberOfStocks, double stockPrice, String stockSymbol) {
        super(investmentId, investmentName, investmentValue);
        this.numberOfStocks = numberOfStocks;
        this.stockPrice = stockPrice;
        this.stockSymbol = stockSymbol;
    }

}
