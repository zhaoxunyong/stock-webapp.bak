package org.stock.fetch.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockHistoryDaily;

@Mapper
public interface StockHistoryDailyMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByAgoOfDayDate(@Param("agoOfDayDate") Date agoOfDayDate);

    int insert(StockHistoryDaily record);

    StockHistoryDaily selectByPrimaryKey(Long id);

    List<StockHistoryDaily> selectAll();

    int updateByPrimaryKey(StockHistoryDaily record);
    
    StockHistoryDaily selectLastStockHistoryDaily(@Param("no") String no);
    
    List<StockHistoryDaily> selectStockHistoryDailys(@Param("stockId") Long stockId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}