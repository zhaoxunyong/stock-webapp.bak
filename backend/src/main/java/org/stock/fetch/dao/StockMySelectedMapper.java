package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockMySelected;

@Mapper
public interface StockMySelectedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockMySelected record);

    StockMySelected selectByPrimaryKey(Long id);
    
    StockMySelected select(@Param("stockId") Long stockId, @Param("selectedType") Long selectedType);

    int update(@Param("stockId") Long stockId, @Param("selectedType") Long selectedType);
    
    int delete(@Param("selectedType") Long selectedType);

    List<StockMySelected> selectAll();
    
    List<StockMySelected> selectByType(@Param("type") Long type);

    int updateByPrimaryKey(StockMySelected record);
}