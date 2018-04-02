package org.stock.fetch.model;

import com.aeasycredit.commons.lang.base.BaseModel;

public class ChangeStockMySelectedType extends BaseModel {
    /** 注释内容 */
    private static final long serialVersionUID = 8326784519862301216L;
    private Long type;
    private String name;
    public Long getType() {
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
