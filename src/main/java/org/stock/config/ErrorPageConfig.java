package org.stock.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
/**
 * 配置内置web服务器
 * @author roy
 *
 */
@Configuration
@EnableAutoConfiguration
public class ErrorPageConfig {
	/**
	 * 配置内置服务器的错误路径页面
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html"));
				container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
				container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403.html"));
			}
		};
	}
}
