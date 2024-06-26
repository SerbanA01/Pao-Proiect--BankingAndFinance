package model.investments;

public class StockInvestment extends Investment {

    private int numberOfStocks;
    private double stockPrice;
    private String stockSymbol;

    public StockInvestment() {
    }
    public StockInvestment(String investmentId, String investmentName, double investmentValue, int numberOfStocks, double stockPrice, String stockSymbol) {
        super(investmentId, investmentName, investmentValue);
        this.numberOfStocks = numberOfStocks;
        this.stockPrice = stockPrice;
        this.stockSymbol = stockSymbol;
    }

    public StockInvestment(Investment investment) {
        super(investment.getInvestmentId(), investment.getInvestmentName(), investment.getInvestmentValue());
    }
    public int getNumberOfStocks() {
        return numberOfStocks;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setNumberOfStocks(int numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    @Override
    public String toString() {
        return "StockInvestment{" +
                "investmentname=" + investmentName +
                ", investmentValue=" + investmentValue +
                ", numberOfStocks=" + numberOfStocks +
                ", stockPrice=" + stockPrice +
                ", stockSymbol='" + stockSymbol + '\'' +
                '}';
    }

}
