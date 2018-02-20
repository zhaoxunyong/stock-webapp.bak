package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockNewsExcludeKey;

@Mapper
public interface StockNewsExcludeKeyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockNewsExcludeKey record);

    StockNewsExcludeKey selectByPrimaryKey(Long id);

    List<StockNewsExcludeKey> selectAll(boolean status);

    int updateByPrimaryKey(StockNewsExcludeKey record);
}