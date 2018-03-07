package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockMySelectedType;

@Mapper
public interface StockMySelectedTypeMapper {
    int deleteByType(Long type);

    int insert(StockMySelectedType record);

    StockMySelectedType selectByPrimaryKey(Long id);

    List<StockMySelectedType> selectAll();

    List<StockMySelectedType> selectByStockId(Long stockId);

    int updateByPrimaryKey(StockMySelectedType record);
}