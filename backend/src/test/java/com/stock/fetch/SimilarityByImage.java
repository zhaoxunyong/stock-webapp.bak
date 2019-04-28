package com.stock.fetch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Base64Utils;

import com.aeasycredit.commons.httpclient.HttpclientUtils;
import com.aeasycredit.commons.lang.exception.BusinessException;
import com.aeasycredit.commons.lang.utils.CollectionsUtils;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class SimilarityByImage {
    
    public static String parse(String fileA, String fileB) throws Exception {
        String url = "http://192.168.63.202:7010/face/tool/multimodel/similarityByImage";
        List<NameValuePair> params = Lists.newArrayList();
//        String fileA = "D:\\dave\\users_67506\\users\\453207730720342016\\448646219796578304\\5\\16\\2018\\Idcard-[72.81800]-58ed80f2c6f2d0e1.jpg";
//        String fileB = "D:\\dave\\users_67506\\users\\453207730720342016\\448646219796578304\\5\\16\\2018\\LivePhoto-[72.81800]-accff62d009d9831.jpg";
        String imgABase64 = toString(fileA);
        String imgBBase64 = toString(fileB);
        params.add(new BasicNameValuePair("imgA", imgABase64));
        params.add(new BasicNameValuePair("typeA", "0"));
        params.add(new BasicNameValuePair("imgB", imgBBase64));
        params.add(new BasicNameValuePair("typeB", "0"));
        String resultValue = HttpclientUtils.post(url, params);
        DocumentContext cocumentContext = JsonPath.parse(resultValue);
        int result = cocumentContext.read("$.result");
        if(result == 0) {
            // success
            double score = cocumentContext.read("$.score");
            int elapse = cocumentContext.read("$.elapse");
            String info = cocumentContext.read("$.info");
//            System.out.println("result:"+result+"/score:"+score+"/elapse:"+elapse+"/info:"+info);
            return FilenameUtils.getName(fileA)+","+FilenameUtils.getName(fileB)+","+score+","+elapse;
        } else {
            // failed
            throw new BusinessException("error, This result is:--->"+resultValue);
        }
    }
    
    public static String toString(String filePath) throws IOException {
        InputStream input = new FileInputStream(new File(filePath));
        try {
            return Base64Utils.encodeToString(IOUtils.toByteArray(input));
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
    
    /**
     * 根据指定路径获取所有文件夹列表(不包括文件,或隐藏文件夹) 
     * @param filepath 
     * @param ext 
     * @return 返回List，如果传入的路径不对或扩展名为空，则返回空List。 
     */ 
    public static List<File> getFileList(String filepath) {
        if (StringUtils.isEmpty(filepath))
            return Collections.emptyList();
        File file = new File(filepath);
        if (!file.exists() || !file.isDirectory())
            return Collections.emptyList();
        Collection<File> files = FileUtils.listFilesAndDirs(file, new IOFileFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return false;
            }
            
            @Override
            public boolean accept(File file) {
                // TODO Auto-generated method stub
                return false;
            }
        }, new IOFileFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return true;
            }
            
            @Override
            public boolean accept(File file) {
                // TODO Auto-generated method stub
                return true;
            }
        });
        List<File> fileList = new ArrayList<File>();
        for (File f : files) {
            if (f.isDirectory() && !f.isHidden()) {
                fileList.add(f);
            }
        }
        return fileList;
    }
    
    public static List<File> getFiles(File file) {
        File[] files = file.listFiles();
        if(files == null || files.length == 0) {
            return Lists.newArrayList();
        }
        List<File> allFiles = Lists.newArrayList();
        for(File f : files) {
            if(f.isFile()) {
                allFiles.add(f);
            }
        }
        return allFiles;
    }
    
    public static void main(String[] args) throws Exception {
        File out = new File("d:/es/result.csv");
        File out1 = new File("d:/es/result1.csv");
        System.out.println(FileUtils.readLines(out).size());
        Set<String> lines = Sets.newHashSet(FileUtils.readLines(out));/*.stream().filter(s-> {
            String[] arrs = s.split("[,]");
            System.out.println(arrs[0]+"/"+arrs[1]);
            return true;
        }).collect(Collectors.toList());*/
        System.out.println(lines.size());
        for(String line : lines) {
            String[] arrs = line.split("[,]");
            float similar1 = Float.parseFloat(StringUtils.substringBetween(arrs[0], "[", "]"));
            String reson1 = similar1>=30?"Y":"N";
            float similar2 = Float.parseFloat(new BigDecimal(arrs[2]).multiply(new BigDecimal(100)).toString());
            String reson2 = similar2>=70?"Y":"N";
            String result = arrs[0]+","+arrs[1]+","+similar1+","+reson1+","+similar2+","+reson2;
            if(!reson1.equals(reson2)) {
                result += ",N";
            }
//            System.out.println(result);
            FileUtils.write(out1, result+"\n", true);
            
        }
        
        /*String parentPath = "D:\\dave\\users_67506\\users";
        List<File> folders = getFileList(parentPath);
        for(File folder : folders) {
            try {
                List<File> files = getFiles(folder);
                if(CollectionsUtils.isNotEmpty(files) && files.size() > 1) {
//                    System.out.println(files);
                    String str = parse(files.get(0).getPath(),files.get(1).getPath())+"\n";
                    FileUtils.write(out, str, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        
    }
    
}
