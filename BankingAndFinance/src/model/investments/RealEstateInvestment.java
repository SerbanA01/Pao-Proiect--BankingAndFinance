package model.investments;

public class RealEstateInvestment extends Investment {

    private double surface;
    private double pricePerSquareMeter;
    private String address;
    private String propertyType;
    private String annualYield;

    public RealEstateInvestment() {
    }

    public RealEstateInvestment(String investmentId, String investmentName, double investmentValue, double surface, double pricePerSquareMeter, String address, String propertyType, String annualYield) {
        super(investmentId, investmentName, investmentValue);
        this.surface = surface;
        this.pricePerSquareMeter = pricePerSquareMeter;
        this.address = address;
        this.propertyType = propertyType;
        this.annualYield = annualYield;
    }

    public RealEstateInvestment(Investment investment) {
        super(investment.getInvestmentId(), investment.getInvestmentName(), investment.getInvestmentValue());
    }

    public double getSurface() {
        return surface;
    }

    public double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public String getAddress() {
        return address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getAnnualYield() {
        return annualYield;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public void setPricePerSquareMeter(double pricePerSquareMeter) {
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setAnnualYield(String annualYield) {
        this.annualYield = annualYield;
    }

    @Override
    public String toString() {
        return "RealEstateInvestment{" +
                "investmentname=" + investmentName +
                ", investmentValue=" + investmentValue +
                ", surface=" + surface +
                ", pricePerSquareMeter=" + pricePerSquareMeter +
                ", address='" + address + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", annualYield='" + annualYield + '\'' +
                '}';
    }


}
