package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockImportantNews;

@Mapper
public interface StockImportantNewsMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByFroms(String froms);

    int insert(StockImportantNews record);

    StockImportantNews selectByPrimaryKey(Long id);

    List<StockImportantNews> selectAll(@Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    List<StockImportantNews> selectExclude(@Param("keys")String keys, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    List<StockImportantNews> selectInclude(@Param("keys")String keys, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    int count();
    
    int excludeCount(@Param("keys")String keys);
    
    int includeCount(@Param("keys")String keys);

    int updateByPrimaryKey(StockImportantNews record);
}