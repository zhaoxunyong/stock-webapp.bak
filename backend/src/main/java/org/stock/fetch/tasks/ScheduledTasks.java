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
    
    public static volatile boolean IS_FETCH_IMPORTANT_NEW = false;
    
    public static volatile boolean IS_FETCH_NEW = false;
    
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Logger logger = LoggerFactory.getLogger(this.getClass());?
//    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    
    @Autowired
    private FetchService fetchService;
    
    @Scheduled(cron="0 */8 8-23 * * ?")
    public void fetchImportantNews() throws Exception {
        if(!IS_FETCH_IMPORTANT_NEW) {
            try {
                IS_FETCH_IMPORTANT_NEW = true;
                logger.info("fetchImportantNews start--->"+DatesUtils.YYMMDDHHMMSS.toString());
                
                fetchService.fetchImportantLatestNews();
                logger.info("fetchImportantNews end--->"+DatesUtils.YYMMDDHHMMSS.toString());
                
                // 獲取所有股票
//                fetchService.fetchAll();
            } finally {
                IS_FETCH_IMPORTANT_NEW = false;
            }
        }
    }
    
//    @Scheduled(fixedRate = 999999999)
    /**
     * 启动时执行一次，之后每隔20分钟执行一次  
     */
//    @Scheduled(fixedRate = 20 * 60 * 1000) 
    @Scheduled(cron="0 */15 8-23 * * ?")
    public void fetchNews() throws Exception {
        if(!IS_FETCH_NEW) {
            try {
                IS_FETCH_NEW = true;
                /* logger.info("fetchAll start--->"+DatesUtils.YYMMDDHHMMSS.toString());
                // 獲取所有股票的信息
                fetchService.fetchAll();
                logger.info("fetchAll end--->"+DatesUtils.YYMMDDHHMMSS.toString());*/
                
                logger.info("fetchNews start--->"+DatesUtils.YYMMDDHHMMSS.toString());
                fetchService.fetchLatestNews();
                logger.info("fetchNews end--->"+DatesUtils.YYMMDDHHMMSS.toString());
                
                // 獲取所有股票
//                fetchService.fetchAll();
            } finally {
                IS_FETCH_NEW = false;
            }
        }
    }
    
    /**
     * 凌晨1点执行
     */
  @Scheduled(cron="0 0 1 * * ?")
  public void fetchAll() throws Exception {
      fetchService.fetchAll();
      }
}
