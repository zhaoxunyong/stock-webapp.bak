package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockNewsKey;

@Mapper
public interface StockNewsKeyMapper {
    
    int deleteByPrimaryKey(Long id);
    
    int deleteByType(@Param("type") Integer type);

    int insert(StockNewsKey record);

    StockNewsKey selectByPrimaryKey(Long id);

    List<StockNewsKey> selectAll();

    List<StockNewsKey> selectByType(@Param("type") Integer type);

    int updateByPrimaryKey(StockNewsKey record);
}