package org.stock.fetch.api.dto;

import com.aeasycredit.commons.lang.base.BaseDto;

public class ChangeStockMySelectedTypeDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = 348942325707581700L;
    private String type;
    private String name;
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
        this.name = name;
    }
    
}
