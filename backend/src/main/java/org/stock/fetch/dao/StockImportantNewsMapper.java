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
    
    int count();

    int updateByPrimaryKey(StockImportantNews record);
}