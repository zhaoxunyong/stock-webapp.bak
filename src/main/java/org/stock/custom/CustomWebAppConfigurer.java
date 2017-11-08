package org.stock.custom;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.stock.interceptor.LoginInterceptor;

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
