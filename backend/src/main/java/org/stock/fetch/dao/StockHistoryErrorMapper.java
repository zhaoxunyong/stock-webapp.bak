package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockHistoryError;

@Mapper
public interface StockHistoryErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockHistoryError record);

    StockHistoryError selectByPrimaryKey(Long id);

    List<StockHistoryError> selectAll();

    int updateByPrimaryKey(StockHistoryError record);
}