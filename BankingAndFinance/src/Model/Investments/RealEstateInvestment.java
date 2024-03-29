package Model.Investments;

public class RealEstateInvestment extends Investment{

        private double surface;
        private double pricePerSquareMeter;
        private String address;
        private String propertyType;
        private String annualYield;

        public RealEstateInvestment(String investmentId, String investmentName, double investmentValue, double surface, double pricePerSquareMeter, String address, String propertyType, String annualYield) {
            super(investmentId, investmentName, investmentValue);
            this.surface = surface;
            this.pricePerSquareMeter = pricePerSquareMeter;
            this.address = address;
            this.propertyType = propertyType;
            this.annualYield = annualYield;
        }
}
