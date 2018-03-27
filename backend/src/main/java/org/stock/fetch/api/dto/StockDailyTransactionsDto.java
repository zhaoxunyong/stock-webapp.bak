package org.stock.fetch.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.aeasycredit.commons.lang.base.BaseDto;

public class StockDailyTransactionsDto extends BaseDto {

	private static final long serialVersionUID = -2211569900888379422L;

	private String id;

    private String stockId;
    
    private String no;

    private String company;

    private String accountNo;

    private String currency;

    private String txDate;

    private String txKind;

    private String quantity;

    private String txPrice;

    private String txAmount;

    private String fee;

    private String txTallage;

    private String zsTallage;

    private String financingAmount;

    private String cashDeposit;

    private String interest;

    private String debFee;

    private String customerCharge;

    private String profit;

    private String payRate;

    private String holdingCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
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

    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }

    public String getTxKind() {
        return txKind;
    }

    public void setTxKind(String txKind) {
        this.txKind = txKind;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTxPrice() {
        return txPrice;
    }

    public void setTxPrice(String txPrice) {
        this.txPrice = txPrice;
    }

    public String getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(String txAmount) {
        this.txAmount = txAmount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTxTallage() {
        return txTallage;
    }

    public void setTxTallage(String txTallage) {
        this.txTallage = txTallage;
    }

    public String getZsTallage() {
        return zsTallage;
    }

    public void setZsTallage(String zsTallage) {
        this.zsTallage = zsTallage;
    }

    public String getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(String financingAmount) {
        this.financingAmount = financingAmount;
    }

    public String getCashDeposit() {
        return cashDeposit;
    }

    public void setCashDeposit(String cashDeposit) {
        this.cashDeposit = cashDeposit;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getDebFee() {
        return debFee;
    }

    public void setDebFee(String debFee) {
        this.debFee = debFee;
    }

    public String getCustomerCharge() {
        return customerCharge;
    }

    public void setCustomerCharge(String customerCharge) {
        this.customerCharge = customerCharge;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getHoldingCost() {
        return holdingCost;
    }

    public void setHoldingCost(String holdingCost) {
        this.holdingCost = holdingCost;
    }

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
    
}