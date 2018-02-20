/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： 2018年1月12日
 * 项目： wenchun-webapp
 */
package org.stock.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

/**
 * <功能简述><br>
 * <功能详细描述>
 * 
 * @author Dave.zhao
 * @version [版本号, 2018年1月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class FileMd5Utils {
    
    private FileMd5Utils() {
    }
    
    public static String getMD5(File file) throws IOException {
        String md5 = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fileInputStream));
            fileInputStream.close();
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
        return md5;
    }
}
