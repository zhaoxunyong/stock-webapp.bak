/*
 * 描述： 合同Model轉Dto類
 * 修改人： Weng.weng
 * 修改时间： Jun 14, 2017
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import java.util.Date;

import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockDailyTransactionsDto;
import org.stock.fetch.constant.BuyTypeEnum;
import org.stock.fetch.model.StockDailyTransactions;

import com.aeasycredit.commons.lang.utils.DatesUtils;
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
public class StockDailyTransactionsModelDtoMapping extends PropertyMapConfigurerSupport<StockDailyTransactions, StockDailyTransactionsDto> {

    @Override
    public PropertyMap<StockDailyTransactions, StockDailyTransactionsDto> mapping() {
        return new PropertyMap<StockDailyTransactions, StockDailyTransactionsDto>() {
            @Override
            protected void configure() {
                map().setId(ObjectsUtils.toString(source.getId()));
                map().setStockId(ObjectsUtils.toString(source.getStockId()));
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.YYMMDD2.toString(context.getSource());
                }).map(source.getTxDate(), destination.getTxDate());
                using((MappingContext<Integer, String> context) -> {
                    return context.getSource().intValue() == BuyTypeEnum.BUY.getType() ? BuyTypeEnum.BUY.getName() : BuyTypeEnum.SELL.getName();
                }).map(source.getTxKind(), destination.getTxKind());
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
