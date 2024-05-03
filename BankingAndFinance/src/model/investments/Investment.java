package model.investments;

public class Investment {

    private String investmentId;
    private String investmentName;
    private double investmentValue;


    public Investment() {
    }
    public Investment(String investmentId, String investmentName, double investmentValue) {
        this.investmentId = investmentId;
        this.investmentName = investmentName;
        this.investmentValue = investmentValue;
    }

    public String getInvestmentId() {
        return investmentId;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public double getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(double investmentValue) {
        this.investmentValue = investmentValue;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public void setInvestmentId(String investmentId) {
        this.investmentId = investmentId;
    }





}
