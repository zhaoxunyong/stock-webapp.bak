package org.stock.fetch.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockHistoryError;

@Mapper
public interface StockHistoryErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockHistoryError record);

    StockHistoryError selectByPrimaryKey(Long id);

    List<StockHistoryError> selectAllByStatus(@Param("status") Integer status);

    int updateByPrimaryKey(StockHistoryError record);
    
    int deleteByDate(@Param("no") String no, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}