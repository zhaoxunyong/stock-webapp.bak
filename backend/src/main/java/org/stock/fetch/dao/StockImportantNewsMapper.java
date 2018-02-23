package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockImportantNews;

@Mapper
public interface StockImportantNewsMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByFroms(String froms);

    int insert(StockImportantNews record);

    StockImportantNews selectByPrimaryKey(Long id);

    List<StockImportantNews> selectAll();

    int updateByPrimaryKey(StockImportantNews record);
}