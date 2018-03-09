/*
 * 描述： 合同Model轉Dto類
 * 修改人： Weng.weng
 * 修改时间： Jun 14, 2017
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockNewsKeyDto;
import org.stock.fetch.model.StockNewsKey;

import com.aeasycredit.commons.lang.utils.ObjectsUtils;
import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

/**
 * StockNewsKeyModelDtoMapping
 */
@Component
public class StockNewsKeyModelDtoMapping extends PropertyMapConfigurerSupport<StockNewsKey, StockNewsKeyDto> {

    @Override
    public PropertyMap<StockNewsKey, StockNewsKeyDto> mapping() {
        return new PropertyMap<StockNewsKey, StockNewsKeyDto>() {
            @Override
            protected void configure() {
                map().setId(ObjectsUtils.toString(source.getId()));
            }
        };
    }
    
    
    
}
