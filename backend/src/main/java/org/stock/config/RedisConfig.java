package org.stock.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
