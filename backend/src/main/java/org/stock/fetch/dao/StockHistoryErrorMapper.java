package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockHistoryError;

@Mapper
public interface StockHistoryErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockHistoryError record);

    StockHistoryError selectByPrimaryKey(Long id);

    List<StockHistoryError> selectAllByStatus(@Param("status") Integer status, @Param("type") Integer type);
    
    List<StockHistoryError> selectAllByStatusByNo(@Param("no") String no, @Param("status") Integer status, @Param("type") Integer type);

    int updateByPrimaryKey(StockHistoryError record);

    int updateErrCount(Long id);

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
//    int deleteByDate(@Param("no") String no, @Param("type") Integer type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}