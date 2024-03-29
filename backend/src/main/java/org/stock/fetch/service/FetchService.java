package org.stock.fetch.service;

import java.io.IOException;
import java.util.List;

import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockType;

public interface FetchService {
    
    public void fetchStocks(StockType stockType) throws Exception;
    
    /**
     * 獲取所有股票的信息
     */
    public void fetchAllMyStock() throws Exception;
    
    public void fetchStocks(StockType stockType) throws Exception;
    
    /**
     * 細產類別
     */
//    public void fetchDetail() throws Exception;
    
    /**
     * 獲取某個股票的新聞
     */
    public List<StockNews> fetchNews(StockData stockData, int fetchPage) throws Exception;
    public void fetchLatestNews(StockData stockData) throws Exception;
    public void fetchNews(int fetchPage) throws Exception;
    public void fetchLatestNews() throws Exception;
    
    /**
     * 獲取重點新聞
     */
    public void fetchImportantLatestNews() throws Exception;
    public List<StockImportantNews> fetchImportantNews(int fetchPage) throws Exception;
    
    /**
     * 獲取某只股票的歷史交易數據
     */
//    public void fetchAllHistory(String startDate, String endDate) throws Exception;
    public void fetchAllHistory() throws Exception;
    public void refetchAllHistory() throws Exception;
    public void fetchHistory(String no) throws Exception;
    
    public void fetchAllHistoryDaily() throws Exception;
    public void refetchAllHistoryDaily() throws Exception;
    public void fetchCurrentHistoryDaily(String no) throws Exception;
//    public void fetchHistory(String no, LocalDate startDate, LocalDate endDate) throws Exception;
    
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
