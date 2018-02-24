package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockMySelectedType;

@Mapper
public interface StockMySelectedTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockMySelectedType record);

    StockMySelectedType selectByPrimaryKey(Long id);

    List<StockMySelectedType> selectAll();

    int updateByPrimaryKey(StockMySelectedType record);
}