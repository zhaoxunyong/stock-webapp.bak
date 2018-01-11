package org.stock.fetch.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockMyStore;

@Mapper
public interface StockMyStoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockMyStore record);

    StockMyStore selectByPrimaryKey(Long id);

    StockMyStore select(@Param("stockId") Long stockId, @Param("txDate") Date txDate, @Param("txKind") Integer txKind, @Param("txPrice") BigDecimal txPrice);

    List<StockMyStore> selectAll();

    int updateByPrimaryKey(StockMyStore record);

    int updateByQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);
}