package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockHistory;

@Mapper
public interface StockHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockHistory record);

    StockHistory selectByPrimaryKey(Long id);

    List<StockHistory> selectAll();

    int updateByPrimaryKey(StockHistory record);
}