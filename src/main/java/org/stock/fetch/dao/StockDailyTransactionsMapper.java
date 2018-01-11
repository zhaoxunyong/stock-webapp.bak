package org.stock.fetch.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockDailyTransactions;

@Mapper
public interface StockDailyTransactionsMapper {
    int deleteByPrimaryKey(Long id);
    
    // stock_id，成交日期，种类，成交价，数量
    int delete(@Param("stockId") Long stockId, @Param("txDate") Date txDate, @Param("txKind") Integer txKind, @Param("txPrice") BigDecimal txPrice, @Param("quantity") Integer quantity);

    int insert(StockDailyTransactions record);

    StockDailyTransactions selectByPrimaryKey(Long id);

    List<StockDailyTransactions> selectAll();

    int updateByPrimaryKey(StockDailyTransactions record);
}