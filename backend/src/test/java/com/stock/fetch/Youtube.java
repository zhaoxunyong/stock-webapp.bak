package com.stock.fetch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Youtube {
    
    public static String get(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        int connectTimeoutSec = 60000;
        int socketTimeoutSec = 600000; 
        try {
//            httpClient = HttpClients.createDefault();
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(connectTimeoutSec * 1000)
                    .setConnectionRequestTimeout(connectTimeoutSec * 1000)
                    .setSocketTimeout(socketTimeoutSec * 1000)
                    .build();
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            
            HttpGet httpGet = new HttpGet(url);

            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, Charsets.UTF_8);
        } finally {
            HttpClientUtils.closeQuietly(httpResponse);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }
    
    public static void main(String[] args) throws Exception {
        String apikey = "5fcdb4b9d69f4aab354e1bee80c340c85ef5e5e6";
//        String playlist = "https://www.youtube.com/playlist?list=PLhg5lNNUgpEGls2J-_0hWyogsNpvgCfMC";
        String playlistId = "PLhg5lNNUgpEGls2J-_0hWyogsNpvgCfMC";
        String playlist = "https://api.zhuwei.me/v1/videos/playlists/"+playlistId+"?api-key="+apikey;
        // 获取 YouTube Playlist 内所有视频 ID
        String content = get(playlist);
        
        DocumentContext cocumentContext = JsonPath.parse(content);
        List<String> videoIds = cocumentContext.read("$.response.playlist.videos");
//        System.out.println("videoIds--->"+videoIds);

        String srtRoot = "d:/srt";
        if(videoIds != null && !videoIds.isEmpty()) {
            for(String videoId : videoIds) {
                try {
                    String srtlist = "https://api.zhuwei.me/v1/captions/"+videoId+"?api-key="+apikey;
                    content = get(srtlist);
//                    System.out.println("content1--->"+content);
                    cocumentContext = JsonPath.parse(content);
                    String captionContentUrl = cocumentContext.read("$.response.captions.available_captions[0].caption_content_url");
//                    System.out.println("captionContentUrl--->"+captionContentUrl);
                    String title = cocumentContext.read("$.response.captions.title");
//                  System.out.println("srtContext--->"+srtContext);
                    if(!new File(srtRoot).exists()) {
                        new File(srtRoot).mkdirs();
                    }
                    String srt = srtRoot+"/"+title.replace("?", "").replace("&#39;", "'")+".srt";
                    if(!new File(srt).exists()) {
                        String srtContextUrl = captionContentUrl+"?multilanguage=multilanguage&api-key="+apikey;
                        content = get(srtContextUrl);
//                        System.out.println("content2--->"+content);
                        cocumentContext = JsonPath.parse(content);
                        String srtContext = cocumentContext.read("$.contents.content");
                        FileUtils.writeStringToFile(new File(srt), srtContext);
                    }
                } catch (Exception e) {
                    System.out.println("exception------>"+e.getMessage());
                    System.out.println("videoId------>"+videoId);
                    String errorTxt1 = srtRoot+"/error_playlist1";
                    FileUtils.writeStringToFile(new File(errorTxt1), videoId+"\n", true);
                    String errorTxt2 = srtRoot+"/error_playlist2";
                    FileUtils.writeStringToFile(new File(errorTxt2), videoId+":"+e.getMessage()+"\n", true);
                }
            }
        }
    }
}
