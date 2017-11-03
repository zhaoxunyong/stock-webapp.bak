/*
 * 描述： 登录拦截器
 * 修改人： Weng.weng
 * 修改时间： May 31, 2017
 * 项目： employee-server
 */
package org.stock.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.stock.user.model.User;

import com.aeasycredit.commons.lang.constant.CommonConstant;
import com.aeasycredit.commons.lang.exception.SessionException;

/**
 * 登录拦截器<br>
 * 
 * @author Weng.weng
 * @version [版本号, May 31, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginInterceptor implements HandlerInterceptor {
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String path = this.getServletPath(request);
        
        //判斷是否為Ajax請求 X-Requested-With:XMLHttpRequest
        boolean isAjax = false;
        String requestedWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(requestedWith)) {
            isAjax = true;
        }
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(CommonConstant.SESSION_CONTEXT);
        if (user == null || StringUtils.isBlank(user.getLoginName())) {
//            response.sendRedirect("/loginPage");
//            throw new SessionLostException("访问路径：" + path + "时发生会话丢失");
//            return false;
            
            if (isAjax) {
                throw new SessionException("访问路径：" + path + "时发生会话丢失");
            } else {
                response.sendRedirect("/loginPage");
                return false;
            }
        }
        
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        
    }
    
    /**
     * 获取访问资源的path
     * 
     * @param request
     * @return
     */
    private String getServletPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (StringUtils.isNotEmpty(contextPath)) {
            path = path.substring(contextPath.length());
        }
        return path;
    }
    
}
