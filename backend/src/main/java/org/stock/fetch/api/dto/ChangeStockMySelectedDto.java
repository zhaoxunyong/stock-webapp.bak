package org.stock.fetch.api.dto;

import java.util.List;

import com.aeasycredit.commons.lang.base.BaseDto;

public class ChangeStockMySelectedDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = 348942325707581700L;
    private String selectedType;
    private List<String> stockIds;
    public String getSelectedType() {
        return selectedType;
    }
    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    public List<String> getStockIds() {
        return stockIds;
    }
    public void setStockIds(List<String> stockIds) {
        this.stockIds = stockIds;
    }
    
}
