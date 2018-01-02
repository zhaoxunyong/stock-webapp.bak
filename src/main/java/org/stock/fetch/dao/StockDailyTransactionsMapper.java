package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockDailyTransactions;

@Mapper
public interface StockDailyTransactionsMapper {
    int deleteByPrimaryKey(Long id);
    
    int delete(@Param("no") String no, @Param("name") String name, @Param("quantity") Integer quantity);

    int insert(StockDailyTransactions record);

    StockDailyTransactions selectByPrimaryKey(Long id);

    List<StockDailyTransactions> selectAll();

    int updateByPrimaryKey(StockDailyTransactions record);
}