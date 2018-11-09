package org.stock.webclient;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * WebClientCallBack
 */
@FunctionalInterface
public interface WebClientCallBack<T> {

    
    /**
     * WebClient callback
     * 
     * @param client WebClient
     * @return value
    */
    public T process(WebClient client) throws Exception;
}