package org.stock.fetch.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.stock.fetch.model.StockHistory;

public interface FetchService {
    
    public void fetch(String startDate, String endDate) throws Exception;
    
    List<StockHistory> select(Date startDate, Date endDate);
    
    void importBydailyTransactions(String excelFile) throws IOException;
}
