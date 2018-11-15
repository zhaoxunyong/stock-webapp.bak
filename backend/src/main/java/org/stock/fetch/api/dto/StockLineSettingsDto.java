package org.stock.fetch.api.dto;

import com.aeasycredit.commons.lang.base.BaseModel;

/**
 * StockLineSettingsDto
 */
public class StockLineSettingsDto extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer type;

    private String memo;

    private Integer status;

    private Integer sortOrder;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}