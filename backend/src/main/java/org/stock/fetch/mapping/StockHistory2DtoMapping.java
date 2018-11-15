package org.stock.fetch.mapping;

import java.util.Date;

import org.apache.http.impl.cookie.DateUtils;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockHistoryDto;
import org.stock.fetch.model.StockHistory;

import com.aeasycredit.commons.lang.utils.DatesUtils;
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
public class StockHistory2DtoMapping extends PropertyMapConfigurerSupport<StockHistory, StockHistoryDto> {

    @Override
    public PropertyMap<StockHistory, StockHistoryDto> mapping() {
        return new PropertyMap<StockHistory, StockHistoryDto>() {
            @Override
            protected void configure() {

                map().setId(String.valueOf(source.getId()));
                map().setStockId(String.valueOf(source.getStockId()));
                // d-MMM-yy 9-Jun-14
                using((MappingContext<Date, String> context) -> {
//                	return DateUtils.formatDate(context.getSource(), "d-MMM-yy");
                    return DatesUtils.YYMMDD2.toString(context.getSource());
                }).map(source.getDate(), destination.getDate());
            }
        };
    }
    
    
    
}
