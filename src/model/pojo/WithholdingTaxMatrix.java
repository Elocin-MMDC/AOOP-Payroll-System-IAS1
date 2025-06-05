package model.pojo;

import java.math.BigDecimal;


public class WithholdingTaxMatrix {
    private int withholdingTaxID;
    private BigDecimal minTaxableIncome;
    private BigDecimal maxTaxableIncome;   // nullable
    private BigDecimal baseTax;
    private BigDecimal excessRate;

    public WithholdingTaxMatrix(int withholdingTaxID, BigDecimal minTaxableIncome, BigDecimal maxTaxableIncome, BigDecimal baseTax, BigDecimal excessRate) {
        this.withholdingTaxID = withholdingTaxID;
        this.minTaxableIncome = minTaxableIncome;
        this.maxTaxableIncome = maxTaxableIncome;
        this.baseTax = baseTax;
        this.excessRate = excessRate;
    }

    public int getWithholdingTaxID() {
        return withholdingTaxID;
    }

    public BigDecimal getMinTaxableIncome() {
        return minTaxableIncome;
    }

    public BigDecimal getMaxTaxableIncome() {
        return maxTaxableIncome;
    }

    public BigDecimal getBaseTax() {
        return baseTax;
    }

    public BigDecimal getExcessRate() {
        return excessRate;
    }

    public void setWithholdingTaxID(int withholdingTaxID) {
        this.withholdingTaxID = withholdingTaxID;
    }

    public void setMinTaxableIncome(BigDecimal minTaxableIncome) {
        this.minTaxableIncome = minTaxableIncome;
    }

    public void setMaxTaxableIncome(BigDecimal maxTaxableIncome) {
        this.maxTaxableIncome = maxTaxableIncome;
    }

    public void setBaseTax(BigDecimal baseTax) {
        this.baseTax = baseTax;
    }

    public void setExcessRate(BigDecimal excessRate) {
        this.excessRate = excessRate;
    }
    
}
