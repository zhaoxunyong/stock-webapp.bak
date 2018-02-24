/*
 * 描述： 合同Model轉Dto類
 * 修改人： Weng.weng
 * 修改时间： Jun 14, 2017
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.model.StockMyData;

import com.aeasycredit.commons.lang.utils.ObjectsUtils;
import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

/**
 * 合同Model轉Dto類<br>
 * 
 * @author Weng.weng
 * @version [版本号, Jun 14, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class StockMyDataModelDtoMapping extends PropertyMapConfigurerSupport<StockMyData, StockMyDataDto> {

    @Override
    public PropertyMap<StockMyData, StockMyDataDto> mapping() {
        return new PropertyMap<StockMyData, StockMyDataDto>() {
            @Override
            protected void configure() {

                map().setId(ObjectsUtils.toString(source.getId()));
                map().setStockId(ObjectsUtils.toString(source.getStockId()));
                using((MappingContext<String, String[]> context) -> {
                	if(context.getSource() == null) {
                		return new String[]{};
                	}
                    return context.getSource().split(",");
                }).map(source.getSelectedTypes(), destination.getSelectedTypes());
                /*map().setUserId(ObjectsUtils.toString(source.getUserId()));
                map().setLoanRequestNo(ObjectsUtils.toString(source.getLoanRequestNo()));
                map().setRequestId(ObjectsUtils.toString(source.getRequestId()));
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYYHHMMSS.toString(context.getSource());
                }).map(source.getApplyDate(), destination.getApplyDate());
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYY.toString(context.getSource());
                }).map(source.getValidDate(), destination.getValidDate());
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYY.toString(context.getSource());
                }).map(source.getStartDate(), destination.getStartDate());
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYY.toString(context.getSource());
                }).map(source.getEndDate(), destination.getEndDate());*/
            }
        };
    }
    
    
    
}