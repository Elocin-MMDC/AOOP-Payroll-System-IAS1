package model.pojo;

import java.math.BigDecimal;


public class Allowance {
    private int allowanceID;
    private int allowanceTypeID;
    private BigDecimal amount;
    private String description;

    public Allowance(int allowanceID, int allowanceTypeID, BigDecimal amount, String description) {
        this.allowanceID = allowanceID;
        this.allowanceTypeID = allowanceTypeID;
        this.amount = amount;
        this.description = description;
    }

    public int getAllowanceID() {
        return allowanceID;
    }

    public int getAllowanceTypeID() {
        return allowanceTypeID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setAllowanceID(int allowanceID) {
        this.allowanceID = allowanceID;
    }

    public void setAllowanceTypeID(int allowanceTypeID) {
        this.allowanceTypeID = allowanceTypeID;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
