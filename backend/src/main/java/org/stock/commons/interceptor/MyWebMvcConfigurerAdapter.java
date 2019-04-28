/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： May 26, 2017
 * 项目： app-core
 */
package org.stock.commons.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MyWebMvcConfigurerAdapter
 * 
 * @author Dave.zhao
 * @version [版本号, May 26, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class MyWebMvcConfigurerAdapter 
        extends WebMvcConfigurerAdapter {
    
//    @Autowired
//    private MyHandlerInterceptor myHandlerInterceptor;
    
//    @Autowired
//    private ApplicationContext context;
    
    @Bean
    public MyHandlerInterceptor myHandlerInterceptor() {
        return new MyHandlerInterceptor();
    }
    
/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/docs/**").addResourceLocations("classpath:/swagger/");
        super.addResourceHandlers(registry);
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myHandlerInterceptor()).addPathPatterns("/**");//.excludePathPatterns(excludePath);
        super.addInterceptors(registry);
    }
    
    /*@Bean
    public EmbeddedServletContainerFactory servletContainer() {

      TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

          @Override
          protected void postProcessContext(Context context) {

            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setUserConstraint("CONFIDENTIAL");
            SecurityCollection collection = new SecurityCollection();
            collection.addPattern("/*");
            securityConstraint.addCollection(collection);
            context.addConstraint(securityConstraint);
          }
      };
      tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
      return tomcat;
    }

    private Connector initiateHttpConnector() {

      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
      connector.setScheme("http");
      connector.setPort(80);
      connector.setSecure(false);
      connector.setRedirectPort(NumberUtils.toInt(context.getEnvironment().getProperty("server.port")));
      return connector;
    }*/

}