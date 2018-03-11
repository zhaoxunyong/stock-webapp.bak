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
    
//    List<StockNews> selectByStockId(@Param("stockId")Long stockId, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    List<StockNews> selectExcludeByStockId(@Param("stockId")Long stockId, @Param("excludeKeys")String excludeKeys, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    List<StockNews> selectIncludeByStockId(@Param("stockId")Long stockId, @Param("excludeKeys")String excludeKeys, @Param("includeKeys")String includeKeys, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);

//    int count(@Param("stockId")Long stockId);
    
    int excludeCount(@Param("stockId")Long stockId, @Param("excludeKeys")String excludeKeys);
    
    int includeCount(@Param("stockId")Long stockId, @Param("excludeKeys")String excludeKeys, @Param("includeKeys")String includeKeys);
    
    int updateByPrimaryKey(StockNews record);
}