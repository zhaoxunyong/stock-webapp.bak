package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockDailyTransactionsHistory;

@Mapper
public interface StockDailyTransactionsHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockDailyTransactionsHistory record);

    StockDailyTransactionsHistory selectByPrimaryKey(Long id);

    List<StockDailyTransactionsHistory> selectAll();

    StockDailyTransactionsHistory selectByMd5(String  fileMd5);

    int updateByPrimaryKey(StockDailyTransactionsHistory record);
}