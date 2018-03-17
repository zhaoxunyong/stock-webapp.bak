package org.stock.fetch.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.stock.fetch.service.FetchService;

import com.aeasycredit.commons.lang.utils.DatesUtils;

@Component
public class ScheduledTasks {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    private FetchService fetchService;
    
//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron="*/5 * * * * *")
    public void reportCurrentTime() throws Exception {
        // 自动取新闻第一页
        fetchService.fetchNews(1);
        
        // 自動取重點新聞第一頁
        fetchService.fetchImportantNews(1);
        
        // 獲取所有股票
        fetchService.fetchAll();
        
        
        
        
    }
}
