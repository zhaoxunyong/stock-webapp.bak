/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： Jul 19, 2017
 * 项目： aeasycredit-commons-cache
 */
package com.stock.fetch;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.stock.StockApplication;
import org.stock.fetch.service.FetchService;

/**
 * <功能简述><br>
 * <功能详细描述>
 * 
 * @author Dave.zhao
 * @version [版本号, Jul 19, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StockApplication.class)
public class FetchServiceTest {
    
    @Autowired
    private FetchService fetchService;
    
    @Test
    public void fetch() throws Exception {
        fetchService.fetch();
    }
}
