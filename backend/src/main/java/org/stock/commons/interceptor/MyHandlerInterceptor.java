/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： May 26, 2017
 * 项目： app-core
 */
package org.stock.commons.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
    
    private String getRequestBody(HttpServletRequest request) throws IOException {
        InputStream input = request.getInputStream();
        OutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(input, output);
        return output.toString();
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
    
    // 在请求处理之前进行调用（Controller方法调用之前）
    // 只有返回true才会继续向下执行，返回false取消当前请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        return true;
    }
    
}
