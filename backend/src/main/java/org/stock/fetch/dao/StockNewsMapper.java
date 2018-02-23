package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockNews;

@Mapper
public interface StockNewsMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByStockNews(StockNews record);

    int insert(StockNews record);

    StockNews selectByPrimaryKey(Long id);

    List<StockNews> selectAll();
    
    List<StockNews> selectByStockId(@Param("stockId")Long stockId, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);

    int count(@Param("stockId")Long stockId);
    
    int updateByPrimaryKey(StockNews record);
}