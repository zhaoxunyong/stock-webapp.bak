package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockMySelectedType;

@Mapper
public interface StockMySelectedTypeMapper {
    int deleteByType(Long type);
    
    int deleteAll();

    int insert(StockMySelectedType record);

    StockMySelectedType selectByPrimaryKey(Long id);

    List<StockMySelectedType> selectAll();

    List<StockMySelectedType> selectByStockId(Long stockId);

    int updateByPrimaryKey(StockMySelectedType record);
    
    int renameStockMydataName(@Param("selectedType")  Long selectedType, @Param("name") String name);
}