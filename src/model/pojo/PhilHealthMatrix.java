package model.pojo;

import java.math.BigDecimal;

public class PhilHealthMatrix {
    private int philhealthID;
    private BigDecimal minGrossIncome;
    private BigDecimal maxGrossIncome;
    private BigDecimal premiumRate;
    private BigDecimal contributionAmount;

    public PhilHealthMatrix(int philhealthID, BigDecimal minGrossIncome, BigDecimal maxGrossIncome, BigDecimal premiumRate, BigDecimal contributionAmount) {
        this.philhealthID = philhealthID;
        this.minGrossIncome = minGrossIncome;
        this.maxGrossIncome = maxGrossIncome;
        this.premiumRate = premiumRate;
        this.contributionAmount = contributionAmount;
    }

    public PhilHealthMatrix() {
        
    }
    
    public int getPhilhealthID() {
        return philhealthID;
    }

    public BigDecimal getMinGrossIncome() {
        return minGrossIncome;
    }

    public BigDecimal getMaxGrossIncome() {
        return maxGrossIncome;
    }

    public BigDecimal getPremiumRate() {
        return premiumRate;
    }

    public BigDecimal getContributionAmount() {
        return contributionAmount;
    }

    public void setPhilhealthID(int philhealthID) {
        this.philhealthID = philhealthID;
    }

    public void setMinGrossIncome(BigDecimal minGrossIncome) {
        this.minGrossIncome = minGrossIncome;
    }

    public void setMaxGrossIncome(BigDecimal maxGrossIncome) {
        this.maxGrossIncome = maxGrossIncome;
    }

    public void setPremiumRate(BigDecimal premiumRate) {
        this.premiumRate = premiumRate;
    }

    public void setContributionAmount(BigDecimal contributionAmount) {
        this.contributionAmount = contributionAmount;
    }
    
}
