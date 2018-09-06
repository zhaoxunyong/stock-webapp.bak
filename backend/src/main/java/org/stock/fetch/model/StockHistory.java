package org.stock.fetch.model;

import java.math.BigDecimal;
import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockHistory extends BaseModel {
    private static final long serialVersionUID = -85238337633748064L;

    private Long id;

    private Long stockId;

    private Date date;
    
    private int type;

    private BigDecimal opening;

    private BigDecimal highest;

    private BigDecimal lowest;

    private BigDecimal closing;

    private BigDecimal upsDowns;

    private String riseRate;

    private BigDecimal vol;

    private BigDecimal amount;

    private String per;
    
    private BigDecimal averagevol5;
    
    private BigDecimal average5;
    
    private BigDecimal average10;
    
    private BigDecimal average20;
    
    private BigDecimal average60;

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

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }
    
    public BigDecimal getAveragevol5() {
        return averagevol5;
    }

    public void setAveragevol5(BigDecimal averagevol5) {
        this.averagevol5 = averagevol5;
    }

    public BigDecimal getAverage5() {
        return average5;
    }

    public void setAverage5(BigDecimal average5) {
        this.average5 = average5;
    }

    public BigDecimal getAverage10() {
        return average10;
    }

    public void setAverage10(BigDecimal average10) {
        this.average10 = average10;
    }

    public BigDecimal getAverage20() {
        return average20;
    }

    public void setAverage20(BigDecimal average20) {
        this.average20 = average20;
    }

    public BigDecimal getAverage60() {
        return average60;
    }

    public void setAverage60(BigDecimal average60) {
        this.average60 = average60;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}