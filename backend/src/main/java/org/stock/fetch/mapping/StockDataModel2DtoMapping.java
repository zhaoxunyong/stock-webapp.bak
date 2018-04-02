/*
 * 描述： 合同Model轉Dto類
 * 修改人： Weng.weng
 * 修改时间： Jun 14, 2017
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.model.StockData;

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
public class StockDataModelDtoMapping extends PropertyMapConfigurerSupport<StockData, StockDataDto> {

    @Override
    public PropertyMap<StockData, StockDataDto> mapping() {
        return new PropertyMap<StockData, StockDataDto>() {
            @Override
            protected void configure() {

                map().setId(String.valueOf(source.getId()));
                /*map().setUserId(String.valueOf(source.getUserId()));
                map().setLoanRequestNo(String.valueOf(source.getLoanRequestNo()));
                map().setRequestId(String.valueOf(source.getRequestId()));
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
