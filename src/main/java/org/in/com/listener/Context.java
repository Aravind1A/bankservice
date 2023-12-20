package org.in.com.listener;

import java.net.URL;

import org.in.com.ehcache.EhcacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

	@Bean(name = "userEhcache")
	public String initializeEhCache() {
		URL url = Context.class.getResource("/ehcache.xml");
		EhcacheManager.InitCacheManager(url);
		return "";
	}

}
