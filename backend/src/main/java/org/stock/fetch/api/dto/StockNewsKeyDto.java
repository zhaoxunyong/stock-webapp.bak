package org.stock.fetch.api.dto;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseDto;

public class StockNewsKeyDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = -1959000611608572249L;

    private String id;

    private String key;

    private Integer type;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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