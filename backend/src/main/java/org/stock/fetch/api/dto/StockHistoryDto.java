package org.stock.fetch.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseDto;

public class StockHistoryDto extends BaseDto {
    private static final long serialVersionUID = -85238337633748064L;

    private String id;

    private String stockId;

    private String date;

    private BigDecimal opening;

    private BigDecimal highest;

    private BigDecimal lowest;

    private BigDecimal closing;

    private String vol;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

}