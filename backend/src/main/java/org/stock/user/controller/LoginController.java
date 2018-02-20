package org.stock.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stock.config.UserConfig;
import org.stock.user.model.User;

import com.aeasycredit.commons.codec.utils.MD5Utils;
import com.aeasycredit.commons.lang.constant.CommonConstant;

@Controller
public class LoginController {
    
    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired
    private UserConfig userConfig;
    
    @RequestMapping("/loginPage")
    public String loginPage() {
       return "login";
    }
    
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, String userName, String password) {
        log.info(userName + "开始登录系统>>>");
        String result = "failure";
        
        try {
            String encPassword = MD5Utils.encode32(password);
            
            // 从配置中获取该用户对应的密码信息
            Map<String, String> loginUserMap = userConfig.getLoginUser();
            String correctPassword = null;
            if (loginUserMap != null) {
                correctPassword = loginUserMap.get(userName);
            }
            
            if (correctPassword != null && correctPassword.equalsIgnoreCase(encPassword)) {
                
                User user = new User();
                user.setLoginName(userName);
                
                // 密码正确
                HttpSession session = request.getSession();
                session.setAttribute(CommonConstant.SESSION_CONTEXT, user);
                //session.setMaxInactiveInterval(60); //test
                result = "success";
            } else {
               // 密码不正确
                log.error("密码不正确。userName：" + userName + " password:" + encPassword);
            }
        } catch (Exception e) {
            log.error("登录失败", e);
        }
        
        return result;
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        session.removeAttribute(CommonConstant.SESSION_CONTEXT);
        session.invalidate();
        return "login";
    }
    
}
