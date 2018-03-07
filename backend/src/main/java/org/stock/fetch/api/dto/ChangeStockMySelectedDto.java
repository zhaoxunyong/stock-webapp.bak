package org.stock.fetch.api.dto;

import java.util.List;

public class ChangeStockMySelectedDto {
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
