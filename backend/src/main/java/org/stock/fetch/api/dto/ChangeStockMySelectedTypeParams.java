package org.stock.fetch.api.dto;

import java.util.List;

import com.aeasycredit.commons.lang.base.BaseDto;

public class ChangeStockMySelectedTypeParams extends BaseDto {
    
    /** 注释内容 */
    private static final long serialVersionUID = 7356811291929970919L;
    
    private List<ChangeStockMySelectedTypeDto> changeStockMySelectedTypeDtos;

    public List<ChangeStockMySelectedTypeDto> getChangeStockMySelectedTypeDtos() {
        return changeStockMySelectedTypeDtos;
    }

    public void setChangeStockMySelectedTypeDtos(List<ChangeStockMySelectedTypeDto> changeStockMySelectedTypeDtos) {
        this.changeStockMySelectedTypeDtos = changeStockMySelectedTypeDtos;
    }
    
}
