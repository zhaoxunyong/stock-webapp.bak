package org.stock.custom;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.stock.interceptor.LoginInterceptor;

/**
 * 
 * 配置拦截器规则信息<br>
 * 
 * @author Weng.weng
 * @version [版本号, May 16, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class CustomWebAppConfigurer extends WebMvcConfigurerAdapter {
    
//    @Autowired
//    private UserService userService;
    
//    @Autowired
//    private MenuConfig menuConfig;
    
    @Value("${interceptor.excludePath}")
    private String[] excludePath;
    
    public void addInterceptors(InterceptorRegistry registry) {

        // 添加菜单访问权限拦截器
        //registry.addInterceptor(new MenuAccessInterceptor(userService, menuConfig)).addPathPatterns("/**").excludePathPatterns("/","/index","/*.jsp");
        
        // 登录拦截器
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/loginPage","/logout","/*.jsp");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
        
        super.addInterceptors(registry);
    }
}
