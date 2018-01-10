package com.stock.fetch;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.stock.StockApplication;
import org.stock.fetch.service.FetchService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=StockApplication.class)
public class FetchServiceTest {
    
    @Autowired
    private FetchService fetchService;
    
    @Test
    public void fetchAll() throws Exception {
        fetchService.fetchAll();
    }
    
    @Test
    public void fetch() throws Exception {
        long stockId = 2881;
        String startDate = "2017/10/01";
        String endDate = "2017/11/01";
        fetchService.fetch(stockId, startDate, endDate);
    }
    
    @Test
    public void importBydailyTransactions() throws IOException {
        String excelFile = "E:/wenchun/bstw.xlsx";
        fetchService.importBydailyTransactions(excelFile);
    }
}
