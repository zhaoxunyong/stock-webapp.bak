/*
 * 描述： <描述>
 * 修改人： Evan.fang
 * 修改时间： 2017年8月28日
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import java.util.Date;

import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.ChangeStockMySelectedTypeDto;
import org.stock.fetch.model.ChangeStockMySelectedType;

import com.aeasycredit.commons.lang.utils.DatesUtils;
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
public class ChangeStockMySelectedTypeDto2ModelMapping extends PropertyMapConfigurerSupport<ChangeStockMySelectedTypeDto, ChangeStockMySelectedType>{

    @Override
    public PropertyMap<ChangeStockMySelectedTypeDto, ChangeStockMySelectedType> mapping() {
        
        return new PropertyMap<ChangeStockMySelectedTypeDto, ChangeStockMySelectedType>(){

            @Override
            protected void configure() {
                using((MappingContext<String, Long> context) -> {
                    return Long.parseLong(context.getSource());
                }).map(source.getType(), destination.getType());
            }
            
        };
    }
    
}
