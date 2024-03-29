package Model.Investments;

public class Investment {

    protected String investmentId;
    protected String investmentName;
    protected double investmentValue;

    public Investment(String investmentId, String investmentName, double investmentValue) {
        this.investmentId = investmentId;
        this.investmentName = investmentName;
        this.investmentValue = investmentValue;
    }



}
