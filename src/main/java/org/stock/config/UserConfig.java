/*
 * 描述： 读取配置文件中的用户名密码信息
 * 修改人： Weng.weng
 * 修改时间： May 31, 2017
 * 项目： employee-server
 */
package org.stock.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

/**
 * 读取配置文件中的用户名密码信息<br>
 * 
 * @author Weng.weng
 * @version [版本号, May 31, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class UserConfig {
    
    private Log log = LogFactory.getLog(this.getClass());
    
    private Map<String, String> loginUser;

    public Map<String, String> getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(Map<String, String> loginUser) {
        this.loginUser = loginUser;
    }
    
    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        
        InputStream inputStream = null;
        try {
            URL url = ResourceUtils.getURL("classpath:config/user.yml");
            
            final ByteSource byteSource = Resources.asByteSource(url);
            inputStream = byteSource.openBufferedStream();
            
            Yaml yaml = new Yaml();
            Map<String, Object> map = (Map<String, Object>) yaml.load(inputStream);
            if (map != null) {
                loginUser = (Map<String, String>)map.get("user");
            }
            
        } catch (FileNotFoundException e) {
            log.error("配置文件不存在", e);
        } catch (IOException e) {
            log.error("讀取配置文件失敗", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    // do nothing
                }
            }
        }
    }
    
}
