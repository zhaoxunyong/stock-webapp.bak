package org.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

@Configuration
public class StockConfiguration {
    
    @Bean
    public WebClient webClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);//设置浏览器  
        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setMaxInMemory(104857600); // unit: bytes 100M
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);//设置css是否生效  
        webClient.getOptions().setJavaScriptEnabled(false);//设置js是否生效  
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax请求  
        webClient.getOptions().setTimeout(30000); // milliseconds
        webClient.waitForBackgroundJavaScript(10000); // timeoutMillis
        return webClient;
    }
    
    @Bean
    public WebClient jsWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);//设置浏览器  
        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setMaxInMemory(104857600); // unit: bytes 100M
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);//设置css是否生效  
        webClient.getOptions().setJavaScriptEnabled(true);//设置js是否生效  
        webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        webClient.setJavaScriptTimeout(10000);//设置JS执行的超时时间
        webClient.waitForBackgroundJavaScript(10000); // timeoutMillis
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax请求  
        webClient.getOptions().setTimeout(30000); // milliseconds
        return webClient;
    }
}
