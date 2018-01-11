package org.stock.fetch.model;

import java.math.BigDecimal;
import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockHistory extends BaseModel {
    private static final long serialVersionUID = -85238337633748064L;

    private Long id;

    private Long stockId;

    private Date date;

    private BigDecimal opening;

    private BigDecimal highest;

    private BigDecimal lowest;

    private BigDecimal closing;

    private BigDecimal upsDowns;

    private String riseRate;

    private String vol;

    private String amount;

    private String per;

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

    public BigDecimal getOpening() {
        return opening;
    }

    public void setOpening(BigDecimal opening) {
        this.opening = opening;
    }

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(BigDecimal highest) {
        this.highest = highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(BigDecimal lowest) {
        this.lowest = lowest;
    }

    public BigDecimal getClosing() {
        return closing;
    }

    public void setClosing(BigDecimal closing) {
        this.closing = closing;
    }

    public BigDecimal getUpsDowns() {
        return upsDowns;
    }

    public void setUpsDowns(BigDecimal upsDowns) {
        this.upsDowns = upsDowns;
    }

    public String getRiseRate() {
        return riseRate;
    }

    public void setRiseRate(String riseRate) {
        this.riseRate = riseRate;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
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