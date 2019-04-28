package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockData;

@Mapper
public interface StockDataMapper {
    int updateAllStatus(boolean status);
    
    int deleteByPrimaryKey(Long id);
    
    int deleteByNo(String no);

    int insert(StockData record);

    StockData selectByPrimaryKey(Long id);

    StockData selectByNo(String no);

    List<StockData> selectAll();
    
    List<StockData> search4StockMyData(@Param("value") String value);
    
    List<StockData> search4StockData(@Param("value") String value);

    int updateByPrimaryKey(StockData record);
    
    void saveCompanyStatus(@Param("id") Long stockId, @Param("companyStatus") String companyStatus);
}