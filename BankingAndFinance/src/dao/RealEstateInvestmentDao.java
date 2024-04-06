package dao;

import java.util.List;
import java.util.ArrayList;
import model.Investments.RealEstateInvestment;

public class RealEstateInvestmentDao {
private static List<RealEstateInvestment> realEstateInvestments = new ArrayList<RealEstateInvestment>();
    public void create(RealEstateInvestment realEstateInvestment) {
        realEstateInvestments.add(realEstateInvestment);
    }
    public void delete(RealEstateInvestment realEstateInvestment) {
        realEstateInvestments.remove(realEstateInvestment);
    }
    public RealEstateInvestment read(String investmentId) {
        if (!realEstateInvestments.isEmpty()) {
            for (RealEstateInvestment r : realEstateInvestments) {
                if (r.getInvestmentId().equals(investmentId)) {
                    return r;
                }
            }
        }
        return null;
    }
}
