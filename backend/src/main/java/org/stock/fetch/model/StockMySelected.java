package org.stock.fetch.model;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockMySelected extends BaseModel {
    /** 注释内容 */
    private static final long serialVersionUID = 3007349442235065690L;

    private Long id;

    private Long stockId;

    private Long selectedType;

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

    public Long getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Long selectedType) {
        this.selectedType = selectedType;
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