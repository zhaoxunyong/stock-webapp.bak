package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockNews;

@Mapper
public interface StockNewsMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByStockNews(StockNews record);

    int insert(StockNews record);

    StockNews selectByPrimaryKey(Long id);

    List<StockNews> selectAll();
    
    List<StockNews> selectByStockId(Long stockId);

    int updateByPrimaryKey(StockNews record);
}