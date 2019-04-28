/*
 * 描述： <描述>
 * 修改人： Evan.fang
 * 修改时间： 2017年8月28日
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockImportantNewsDto;
import org.stock.fetch.api.dto.StockLineSettingsDto;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockLineSettings;

import com.aeasycredit.commons.lang.utils.ObjectsUtils;
import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

@Component
public class StockLineSettingsModel2DtoMapping extends PropertyMapConfigurerSupport<StockLineSettings, StockLineSettingsDto>{

    @Override
    public PropertyMap<StockLineSettings, StockLineSettingsDto> mapping() {
        
        return new PropertyMap<StockLineSettings, StockLineSettingsDto>(){

            @Override
            protected void configure() {
            	map().setId(String.valueOf(source.getId()));
            }
            
        };
    }
    
}
