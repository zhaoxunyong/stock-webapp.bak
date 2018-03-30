/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： May 26, 2017
 * 项目： app-core
 */
package org.stock.commons.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.stock.user.api.dto.UserDto;

import com.aeasycredit.commons.lang.constant.CommonConstant;
import com.aeasycredit.commons.lang.exception.SessionException;
import com.aeasycredit.commons.lang.utils.RegexUtils;

/**
 * MyHandlerInterceptor
 * 
 * @author Dave.zhao
 * @version [版本号, May 26, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyHandlerInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${interceptor.excludePath}")
    private String excludePath;
    
    // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        // do nothing
    }
    
    // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        // do nothing
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
    
    private boolean isNeedFilter(String path) {
        boolean needFilter = false;
        if(StringUtils.isNotBlank(excludePath)) {
            excludePath = excludePath.replaceAll("#.*\n", "").replace("\n", "");
        }
        if(StringUtils.isNotBlank(excludePath)) {
//            List<String> excludePaths = Splitter.on(",").trimResults().splitToList(excludePath);
//            return Arrays.stream(excludePath.split("[,]")).map(p -> p.trim()).anyMatch(p -> p.equalsIgnoreCase(path));
            needFilter = !Arrays.stream(
                excludePath.split("[,]"))
                .map(p -> p.trim().replaceAll("\\.html(?)$", ""))
                .peek(System.out::println)
                .anyMatch(p -> RegexUtils.match(p, path)
            );
        }
        return needFilter;
    }
    
    // 在请求处理之前进行调用（Controller方法调用之前）
    // 只有返回true才会继续向下执行，返回false取消当前请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String path = this.getServletPath(request);
        
//        if(isNeedFilter(path)) {
            //判斷是否為Ajax請求 X-Requested-With:XMLHttpRequest
            boolean isAjax = false;
            String requestedWith = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equalsIgnoreCase(requestedWith)) {
                isAjax = true;
            }
                
            HttpSession session = request.getSession();
            UserDto userDto = (UserDto)session.getAttribute(CommonConstant.SESSION_CONTEXT);
            if (userDto == null || StringUtils.isBlank(userDto.getLoginName())) {
//                response.sendRedirect("/loginPage");
//                throw new SessionLostException("访问路径：" + path + "时发生会话丢失");
//                return false;
                
                if (isAjax) {
                    throw new SessionException("访问路径：" + path + "时发生会话丢失!");
                } else {
                    response.sendRedirect("/#/login");
                    return false;
                }
            }
//        }
        return true;
    }
    
}
