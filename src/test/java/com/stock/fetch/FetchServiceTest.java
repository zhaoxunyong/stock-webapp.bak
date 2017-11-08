package com.stock.fetch;

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
    public void fetch() throws Exception {
        String startDate = "2016/01/01";
        String endDate = "2017/11/01";
        fetchService.fetch(startDate, endDate);
    }
}
