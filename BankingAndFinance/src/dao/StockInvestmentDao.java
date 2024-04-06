package dao;

import java.util.List;
import java.util.ArrayList;
import model.Investments.StockInvestment;

public class StockInvestmentDao {
    private static List<StockInvestment> stockInvestments = new ArrayList<StockInvestment>();
    public void create(StockInvestment stockInvestment) {
        stockInvestments.add(stockInvestment);
    }
    public void delete(StockInvestment stockInvestment) {
        stockInvestments.remove(stockInvestment);
    }
    public StockInvestment read(String investmentId) {
        if (!stockInvestments.isEmpty()) {
            for (StockInvestment s : stockInvestments) {
                if (s.getInvestmentId().equals(investmentId)) {
                    return s;
                }
            }
        }
        return null;
    }
}
