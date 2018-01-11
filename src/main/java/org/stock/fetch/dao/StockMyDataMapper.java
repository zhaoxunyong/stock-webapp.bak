package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockMyData;

@Mapper
public interface StockMyDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockMyData record);

    StockMyData selectByPrimaryKey(Long id);
    
    StockMyData selectByStockId(Long stockId);

    List<StockMyData> selectAll();

    int updateByPrimaryKey(StockMyData record);
}