package ar.com.scienza.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;


@Configuration
@EnableCaching
public class ScienzaCacheConfig extends CachingConfigurerSupport {

	@Override
	@Bean
	public CacheManager cacheManager() {
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		return cacheManager;
	}

	@Bean
	public CacheManager timeoutCacheManager() {
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.SECONDS);
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}
}