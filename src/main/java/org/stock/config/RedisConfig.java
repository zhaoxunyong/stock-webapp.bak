/*
 * 描述： Redis緩存配置
 * 修改人： Weng.weng
 * 修改时间： June 4, 2017
 * 项目： employee-server
 */
package org.stock.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 *  Redis緩存配置<br>
 * 
 * @author Weng.weng
 * @version [版本号, Jun 4, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

//	@Bean
//	public JedisConnectionFactory redisConnectFactory() {
//	    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//	    jedisConnectionFactory.afterPropertiesSet();
//	    return jedisConnectionFactory;
//	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
