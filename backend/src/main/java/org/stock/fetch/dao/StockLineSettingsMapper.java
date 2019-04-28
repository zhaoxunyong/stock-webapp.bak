package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockLineSettings;

@Mapper
public interface StockLineSettingsMapper {
    // int deleteByPrimaryKey(Long id);

    // int insert(StockLineSettings record);

    // StockLineSettings selectByPrimaryKey(Long id);

    // int updateByPrimaryKey(StockLineSettings record);

    List<StockLineSettings> selectAvailableAll();

    int updateBySortOrder(@Param("type") Integer type, @Param("sortOrder") Integer sortOrder);
}