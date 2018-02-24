/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： Jun 15, 2017
 * 项目： app-core
 */
package org.stock.commons.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <功能简述><br>
 * <功能详细描述>
 * 
 * @author Dave.zhao
 * @version [版本号, Jun 15, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyFilter implements Filter {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
        
        BodyReaderHttpServletRequestWrapper httpRequest = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        response.setContentType("application/json;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
        chain.doFilter(httpRequest, httpResponse);  
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // do nothing
    }
    
}
