package org.stock.fetch.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.stock.fetch.service.FetchService;

import com.aeasycredit.commons.lang.utils.DatesUtils;

@Component
public class ScheduledTasks {
    
    public static volatile boolean IS_FETCH_ING = false;
    
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    
    @Autowired
    private FetchService fetchService;
    
//    @Scheduled(fixedRate = 999999999)
    /**
     * 启动时执行一次，之后每隔20分钟执行一次  
     */
//    @Scheduled(fixedRate = 20 * 60 * 1000) 
    @Scheduled(cron="* */20 * * * *")
    public void reportCurrentTime() throws Exception {
        if(!IS_FETCH_ING) {
            try {
                IS_FETCH_ING = true;
                /* logger.info("fetchAll start--->"+DatesUtils.YYMMDDHHMMSS.toString());
                // 獲取所有股票的信息
                fetchService.fetchAll();
                System.out.println("fetchAll end--->"+DatesUtils.YYMMDDHHMMSS.toString());*/
                
                logger.info("fetchNews start--->"+DatesUtils.YYMMDDHHMMSS.toString());
                // 自动取新闻第一页
                fetchService.fetchNews(1);
                System.out.println("fetchNews end--->"+DatesUtils.YYMMDDHHMMSS.toString());
                
                System.out.println("fetchImportantNews start--->"+DatesUtils.YYMMDDHHMMSS.toString());
                
                // 自動取重點新聞第一頁
                fetchService.fetchImportantNews(1);
                System.out.println("fetchImportantNews end--->"+DatesUtils.YYMMDDHHMMSS.toString());
                
                // 獲取所有股票
//                fetchService.fetchAll();
            } finally {
                IS_FETCH_ING = false;
            }
        }
    }
}
