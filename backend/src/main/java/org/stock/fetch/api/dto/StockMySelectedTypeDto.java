package org.stock.fetch.api.dto;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseDto;

public class StockMySelectedTypeDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = -8525636804532784524L;

    private String id;

    private String type;

    private String name;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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