package org.stock.fetch.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockHistory;

@Mapper
public interface StockHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockHistory record);

    StockHistory selectByPrimaryKey(Long id);

    List<StockHistory> selectAll();

    int deleteByDate(@Param("stockId") Long stockId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    int deleteByWeekOrMonth(@Param("stockId") Long stockId, @Param("date") Date date, @Param("type") int type);

    int selectUniqueHistoryCount(@Param("stockId") Long stockId, @Param("date") Date date, @Param("type") int type);
    
    List<StockHistory> selectStockHistory(@Param("stockId") Long stockId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("type") int type);
    
    StockHistory selectLastStockHistory(@Param("no") String no);
    
    /**
     * 计算周、月线。只用到其中的几个字段：
     * StockHistory对象中只有date、opening、closing、highest、lowest、vol会赋值
     * 
     * @param stockId stockId
     * @param date date
     * @param type type
     * @return
     */
    StockHistory selectWeekOrMonthStockHistory(@Param("stockId") Long stockId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("type") int type);

    int updateByPrimaryKey(StockHistory record);
    
    int updateAverage(StockHistory record);
    
    /**
     * 计算收盘均线。只用到其中的几个字段：
     * StockHistory对象中只有average5、average10、average20、average60会赋值
     * 
     * @param stockId stockId
     * @param date date
     * @param type type
     * @return
     */
    StockHistory averageClosing(@Param("stockId") Long stockId, @Param("date") Date date, @Param("type") int type);
    
    /**
     * 计算成立量均线。只用到其中的几个字段：
     * StockHistory对象中只有average5、average10、average20、average60会赋值
     * 
     * @param stockId stockId
     * @param date date
     * @param type type
     * @return
     */
    StockHistory averageVol(@Param("stockId") Long stockId, @Param("date") Date date, @Param("type") int type);
}