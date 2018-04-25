package com.stock.fetch;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.stock.StockApplication;
import org.stock.fetch.service.FetchService;
import org.stock.utils.FileMd5Utils;

import com.aeasycredit.commons.lang.utils.DatesUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=StockApplication.class)
public class FetchServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private FetchService fetchService;
    
    @Test
    public void fetchAll() throws Exception {
        fetchService.fetchAll();
    }
    
    @Test
    public void fetchDetail() throws Exception {
        fetchService.fetchDetail();
    }
    
    @Test
    public void fetchNews() throws Exception {
        fetchService.fetchNews(1);
        fetchService.fetchNews(2);
//        fetchService.fetchNews(3);
//        fetchService.fetchNews(4);
    }
    
    @Test
    public void fetchImportantNews() throws Exception {
//        fetchService.fetchImportantNews(1);
//        fetchService.fetchImportantNews(2);
//        fetchService.fetchImportantNews(3);
//        fetchService.fetchImportantNews(4);
        fetchService.fetchImportantLatestNews();
    }
    
    @Test
    public void fetchHistory() throws Exception {
        String no = "2881";
        String startDate = "2017/10/01";
        String endDate = "2017/11/01";
        fetchService.fetchHistory(no, startDate, endDate);
    }
    
    @Test
    public void importBydailyTransactions() throws IOException {
        String excelFile = "E:/wenchun/bstw.xlsx";
        fetchService.importBydailyTransactions(excelFile);
    }
    
    @Test
    public void importStockMyDatas() throws IOException {
        String excelFile = "/home/zhaoxy/個股資料.xlsx";
        fetchService.importStockMyDatas(excelFile);
    }
    
    @Test
    public void fileMd5() throws IOException {
        String excelFile = "E:/wenchun/bstw.xlsx";
        String md5 = FileMd5Utils.getMD5(new File(excelFile));
        System.out.println("md5===>"+md5);
    }
    
    @Test
    public void task() throws Exception {
        logger.info("fetchNews start--->"+DatesUtils.YYMMDDHHMMSS.toString());
        // 自动取新闻第一页
        fetchService.fetchNews(1);
        System.out.println("fetchNews end--->"+DatesUtils.YYMMDDHHMMSS.toString());
        
        System.out.println("fetchImportantNews start--->"+DatesUtils.YYMMDDHHMMSS.toString());
        
        // 自動取重點新聞第一頁
        fetchService.fetchImportantNews(1);
        System.out.println("fetchImportantNews end--->"+DatesUtils.YYMMDDHHMMSS.toString());
    }
}
