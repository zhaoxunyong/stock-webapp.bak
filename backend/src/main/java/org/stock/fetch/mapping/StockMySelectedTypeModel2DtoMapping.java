/*
 * 描述： <描述>
 * 修改人： Evan.fang
 * 修改时间： 2017年8月28日
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockMySelectedTypeDto;
import org.stock.fetch.model.StockMySelectedType;

import com.aeasycredit.commons.lang.utils.ObjectsUtils;
import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

/**
 * 合同Dto轉Modle<br>
 * 
 * @author Evan.fang
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class StockMySelectedTypeModelDtoMapping extends PropertyMapConfigurerSupport<StockMySelectedType, StockMySelectedTypeDto>{

    @Override
    public PropertyMap<StockMySelectedType, StockMySelectedTypeDto> mapping() {
        
        return new PropertyMap<StockMySelectedType, StockMySelectedTypeDto>(){

            @Override
            protected void configure() {
            	map().setId(String.valueOf(source.getId()));
            	map().setType(String.valueOf(source.getType()));
                /*map().setUserId(LongUtils.string2Long(source.getUserId()));
                map().setLoanRequestNo(LongUtils.string2Long(source.getLoanRequestNo()));
                map().setRequestId(LongUtils.string2Long(source.getRequestId()));
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYYHHMMSS.toDate(context.getSource());
                }).map(source.getApplyDate(), destination.getApplyDate());
                
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYY.toDate(context.getSource());
                }).map(source.getValidDate(), destination.getValidDate());
                
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYY.toDate(context.getSource());
                }).map(source.getStartDate(), destination.getStartDate());
                
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYY.toDate(context.getSource());
                }).map(source.getEndDate(), destination.getEndDate());*/
                
            }
            
        };
    }
    
}
