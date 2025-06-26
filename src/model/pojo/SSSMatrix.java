package model.pojo;

import java.math.BigDecimal;

public class SSSMatrix {
    private int sssID;
    private BigDecimal minGrossIncome;
    private BigDecimal maxGrossIncome;
    private BigDecimal contributionAmount;

    public SSSMatrix(int sssID, BigDecimal minGrossIncome, BigDecimal maxGrossIncome, BigDecimal contributionAmount) {
        this.sssID = sssID;
        this.minGrossIncome = minGrossIncome;
        this.maxGrossIncome = maxGrossIncome;
        this.contributionAmount = contributionAmount;
    }
    
    public SSSMatrix() {
        
    }

    public int getSssID() {
        return sssID;
    }

    public BigDecimal getMinGrossIncome() {
        return minGrossIncome;
    }

    public BigDecimal getMaxGrossIncome() {
        return maxGrossIncome;
    }

    public BigDecimal getContributionAmount() {
        return contributionAmount;
    }

    public void setSssID(int sssID) {
        this.sssID = sssID;
    }

    public void setMinGrossIncome(BigDecimal minGrossIncome) {
        this.minGrossIncome = minGrossIncome;
    }

    public void setMaxGrossIncome(BigDecimal maxGrossIncome) {
        this.maxGrossIncome = maxGrossIncome;
    }

    public void setContributionAmount(BigDecimal contributionAmount) {
        this.contributionAmount = contributionAmount;
    }
    
}
