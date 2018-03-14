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

//    List<StockImportantNews> selectAll(@Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    List<StockImportantNews> selectExclude(@Param("excludeKeys")String excludeKeys, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
    List<StockImportantNews> selectInclude(@Param("excludeKeys")String excludeKeys, @Param("includeKeys")String includeKeys, @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);
    
//    int count();
    
    int excludeCount(@Param("excludeKeys")String excludeKeys);
    
    int includeCount(@Param("excludeKeys")String excludeKeys, @Param("includeKeys")String includeKeys);

    int updateByPrimaryKey(StockImportantNews record);
}