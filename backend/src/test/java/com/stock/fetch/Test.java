package com.stock.fetch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.aeasycredit.commons.json.JsonUtils;
//import com.aeasycredit.hkcash.app.user.dto.AccountInfo;

public class Test {
    public static void main(String[] args) throws IOException {
        String f = "d:/65.txt";
        List<String> strs = FileUtils.readLines(new File(f));
        for(String  str : strs) {
            String dat = StringUtils.substringAfterLast(StringUtils.substringBefore(str, " "),":");
            String accounts = "{"+StringUtils.substringAfter(str, "{");
//            AccountInfo accountInfo = JsonUtils.toObject(accounts, AccountInfo.class);
//            System.out.println("dat:"+dat+",ishkLocation:"+accountInfo.getIshkLocation()+",lat:"+accountInfo.getLat()+",lng:"+accountInfo.getLng());
        }
    }
}
