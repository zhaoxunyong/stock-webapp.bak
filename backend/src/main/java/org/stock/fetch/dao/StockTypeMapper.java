package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockType;

@Mapper
public interface StockTypeMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByName(@Param("name") String name, @Param("type") Integer type);

    int insert(StockType record);

    StockType selectByPrimaryKey(Long id);

    StockType selectByName(@Param("name") String name, @Param("type") Integer type);

    List<StockType> selectAll();

    int updateByPrimaryKey(StockType record);
}