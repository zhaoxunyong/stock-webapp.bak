package org.stock.webclient;

import java.util.List;

import com.aeasycredit.commons.lang.exception.DatasAccessException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;

/**
 * WebClientUtils
 */
public class WebClientUtils {

    private static WebClient getWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);// 设置浏览器
        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setMaxInMemory(104857600); // unit: bytes 100M
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);// 设置css是否生效
        webClient.getOptions().setJavaScriptEnabled(false);// 设置js是否生效
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置ajax请求
        webClient.getOptions().setTimeout(30000); // milliseconds
        webClient.waitForBackgroundJavaScript(10000); // timeoutMillis
		// webClient.setWebConnection(new InterceptWebConnection(webClient));
		webClient.setWebConnection(new MyWrapper(webClient));
		return webClient;
    }


    public static <T> T process(WebClientCallBack<T> callback) {
        WebClient webClient = null;
        try {
            webClient = getWebClient();
            return callback.process(webClient);
        } catch (Exception e) {
            throw new DatasAccessException(e);
        } finally {
            try {
                if (webClient != null) {
                    final List<WebWindow> windows = webClient.getWebWindows();
                    for (final WebWindow wd : windows) {
                        wd.getJobManager().removeAllJobs();
                    }
                    webClient.getCurrentWindow().getJobManager().removeAllJobs();
                    webClient.close();
                    // System.gc();
                }
            } catch (Exception e) {
                // do nothing
            }
        }
    }

}