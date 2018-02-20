package org.stock.fetch.model;

import java.math.BigDecimal;
import java.util.Date;

public class StockMyStore {
    private Long id;

    private Long stockId;

    private BigDecimal txPrice;

    private Date txDate;

    private Integer txKind;

    private Integer quantity;

    private Boolean status;

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

    public BigDecimal getTxPrice() {
        return txPrice;
    }

    public void setTxPrice(BigDecimal txPrice) {
        this.txPrice = txPrice;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Integer getTxKind() {
        return txKind;
    }

    public void setTxKind(Integer txKind) {
        this.txKind = txKind;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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