package org.stock.fetch.model;

import java.math.BigDecimal;
import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockHistoryDaily extends BaseModel {
    private static final long serialVersionUID = -3622394611538885530L;

    private Long id;

    private Long stockId;

    private Date date;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal vol;

    private BigDecimal totalAmount;
    
    private BigDecimal upsDowns;

    private BigDecimal pratyaksam;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getUpsDowns() {
        return upsDowns;
    }

    public void setUpsDowns(BigDecimal upsDowns) {
        this.upsDowns = upsDowns;
    }

    public BigDecimal getPratyaksam() {
        return pratyaksam;
    }

    public void setPratyaksam(BigDecimal pratyaksam) {
        this.pratyaksam = pratyaksam;
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