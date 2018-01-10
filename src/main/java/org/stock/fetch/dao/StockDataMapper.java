package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockData;

@Mapper
public interface StockDataMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByNo(String no);

    int insert(StockData record);

    StockData selectByPrimaryKey(Long id);

    StockData selectByNo(String no);

    List<StockData> selectAll();

    int updateByPrimaryKey(StockData record);
}