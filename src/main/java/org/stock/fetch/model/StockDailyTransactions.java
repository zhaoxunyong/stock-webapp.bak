package org.stock.fetch.model;

import java.math.BigDecimal;
import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockDailyTransactions extends BaseModel {
    private static final long serialVersionUID = 1406821448027386846L;

    private Long id;

    private Long stockId;

    private Integer no;

    private String name;

    private String accountNo;

    private String currency;

    private Date txDate;

    private String txKind;

    private Integer quantity;

    private BigDecimal txPrice;

    private BigDecimal txAmount;

    private BigDecimal fee;

    private BigDecimal txTallage;

    private BigDecimal zsTallage;

    private BigDecimal financingAmount;

    private BigDecimal cashDeposit;

    private BigDecimal interest;

    private BigDecimal debFee;

    private BigDecimal customerCharge;

    private BigDecimal profit;

    private BigDecimal payRate;

    private BigDecimal holdingCost;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxKind() {
        return txKind;
    }

    public void setTxKind(String txKind) {
        this.txKind = txKind == null ? null : txKind.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTxPrice() {
        return txPrice;
    }

    public void setTxPrice(BigDecimal txPrice) {
        this.txPrice = txPrice;
    }

    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTxTallage() {
        return txTallage;
    }

    public void setTxTallage(BigDecimal txTallage) {
        this.txTallage = txTallage;
    }

    public BigDecimal getZsTallage() {
        return zsTallage;
    }

    public void setZsTallage(BigDecimal zsTallage) {
        this.zsTallage = zsTallage;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public BigDecimal getCashDeposit() {
        return cashDeposit;
    }

    public void setCashDeposit(BigDecimal cashDeposit) {
        this.cashDeposit = cashDeposit;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getDebFee() {
        return debFee;
    }

    public void setDebFee(BigDecimal debFee) {
        this.debFee = debFee;
    }

    public BigDecimal getCustomerCharge() {
        return customerCharge;
    }

    public void setCustomerCharge(BigDecimal customerCharge) {
        this.customerCharge = customerCharge;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getPayRate() {
        return payRate;
    }

    public void setPayRate(BigDecimal payRate) {
        this.payRate = payRate;
    }

    public BigDecimal getHoldingCost() {
        return holdingCost;
    }

    public void setHoldingCost(BigDecimal holdingCost) {
        this.holdingCost = holdingCost;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}