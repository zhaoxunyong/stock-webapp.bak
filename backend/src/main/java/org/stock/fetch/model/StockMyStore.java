package org.stock.fetch.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockMyStore extends BaseModel {
    /** 注释内容 */
    private static final long serialVersionUID = -7138985799160787032L;

    private Long id;

    private Long stockId;
    
    @Transient
    private String no;
    
    @Transient
    private String company;

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