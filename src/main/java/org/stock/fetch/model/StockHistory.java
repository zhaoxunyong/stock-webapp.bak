package org.stock.fetch.model;

import java.util.Date;

public class StockHistory {
    private Long id;

    private String stockId;

    private String date;

    private String opening;

    private String highest;

    private String lowest;

    private String closing;

    private String upsDowns;

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

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening == null ? null : opening.trim();
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest == null ? null : highest.trim();
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest == null ? null : lowest.trim();
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing == null ? null : closing.trim();
    }

    public String getUpsDowns() {
        return upsDowns;
    }

    public void setUpsDowns(String upsDowns) {
        this.upsDowns = upsDowns == null ? null : upsDowns.trim();
    }

    public String getRiseRate() {
        return riseRate;
    }

    public void setRiseRate(String riseRate) {
        this.riseRate = riseRate == null ? null : riseRate.trim();
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol == null ? null : vol.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per == null ? null : per.trim();
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