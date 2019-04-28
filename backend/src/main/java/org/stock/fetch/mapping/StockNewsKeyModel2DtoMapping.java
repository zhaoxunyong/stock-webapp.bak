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
public class StockNewsKeyModel2DtoMapping extends PropertyMapConfigurerSupport<StockNewsKey, StockNewsKeyDto> {

    @Override
    public PropertyMap<StockNewsKey, StockNewsKeyDto> mapping() {
        return new PropertyMap<StockNewsKey, StockNewsKeyDto>() {
            @Override
            protected void configure() {
                map().setId(String.valueOf(source.getId()));
            }
        };
    }
    
    
    
}
