/*
 * 描述： 合同Model轉Dto類
 * 修改人： Weng.weng
 * 修改时间： Jun 14, 2017
 * 项目： los-core
 */
package org.stock.fetch.mapping;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

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
                map().setId(String.valueOf(source.getId()));
                
                map().setStockId(String.valueOf(source.getStockId()));
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.YYMMDD2.toString(context.getSource());
                }).map(source.getTxDate(), destination.getTxDate());
                
                using((MappingContext<Integer, String> context) -> {
                    return context.getSource().intValue() == BuyTypeEnum.BUY.getType() ? BuyTypeEnum.BUY.getName() : BuyTypeEnum.SELL.getName();
                }).map(source.getTxKind(), destination.getTxKind());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getTxPrice(), destination.getTxPrice());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getTxAmount(), destination.getTxAmount());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getTxTallage(), destination.getTxTallage());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getZsTallage(), destination.getZsTallage());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getCashDeposit(), destination.getCashDeposit());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getInterest(), destination.getInterest());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getDebFee(), destination.getDebFee());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getCustomerCharge(), destination.getCustomerCharge());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getProfit(), destination.getProfit());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getPayRate(), destination.getPayRate());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getHoldingCost(), destination.getHoldingCost());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getFee(), destination.getFee());
                
                using((MappingContext<BigDecimal, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getFinancingAmount(), destination.getFinancingAmount());
                
                using((MappingContext<Integer, String> context) -> {
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); 
                    return formatter.format(context.getSource());
                }).map(source.getQuantity(), destination.getQuantity());
            }
        };
    }
    
    
    
}
