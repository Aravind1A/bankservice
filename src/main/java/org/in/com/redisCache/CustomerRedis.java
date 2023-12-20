package org.in.com.redisCache;

import org.in.com.dto.AuthDto;
import org.in.com.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CustomerRedis {

	@Autowired
	private CacheManager cacheManager;

	public CustomerDto getCustomerData(AuthDto authDto,String code) {
		Cache customerCache = cacheManager.getCache("customercache");
		Cache.ValueWrapper cacheValue = customerCache.get(code);
		if (cacheValue != null) {
			Object cachedData = cacheValue.get();
			System.out.println("Customer(redis) cache stored " + cachedData);
			if (cachedData instanceof CustomerDto) {
				return (CustomerDto) cachedData;
			}
		}
		return null;
	}

	public CustomerDto putCustomerData(String code, CustomerDto customerdto) {
		Cache customercache = cacheManager.getCache("customerCache");
		customercache.put(code, customerdto);
		return null;
	}

}
