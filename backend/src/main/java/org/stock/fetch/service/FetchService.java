package org.stock.fetch.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.stock.fetch.model.StockHistory;

public interface FetchService {
    
    /**
     * 獲取所有股票的信息
     */
    public void fetchAll() throws Exception;
    
    /**
     * 細產類別
     */
    public void fetchDetail() throws Exception;
    
    /**
     * 獲取某個股票的新聞
     */
    public void fetchNews(int fetchPage) throws Exception;
    
    /**
     * 獲取重點新聞
     */
    public void fetchImportantNews(int fetchPage) throws Exception;
    
    /**
     * 獲取某只股票的歷史交易數據
     */
    public void fetchHistory(String no, String startDate, String endDate) throws Exception;
    
//    List<StockHistory> select(Date startDate, Date endDate);
    
    /**
     * 導入每日買賣股票數據
     */
    void importBydailyTransactions(String excelFile) throws IOException;
    
    /**
     * 導入个股
     */
    void importStockMyDatas(String excelFile) throws IOException;
}
