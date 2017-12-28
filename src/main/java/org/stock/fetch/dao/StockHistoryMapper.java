package org.stock.fetch.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockHistory;

@Mapper
public interface StockHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockHistory record);

    StockHistory selectByPrimaryKey(Long id);

    List<StockHistory> selectAll();

    int deleteByDate(@Param("stockId") Long stockId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<StockHistory> select(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    int updateByPrimaryKey(StockHistory record);
}