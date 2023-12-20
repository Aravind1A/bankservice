package org.in.com.ehcache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

public class EhcacheManager {

		public static CacheManager cacheManager;
		public static Cache userCache;

		public static void InitCacheManager(URL url) {
			if (cacheManager == null) {
				cacheManager = new CacheManager(url);
			}
		}

		public static CacheManager getCacheManager() {
			return cacheManager;
		}

		public static void userCache1() {
			Ehcache cache = cacheManager.getCache("userCache");
			cache.removeAll();
			System.out.println("Cache removed Successfully");
		}

		public static Cache getuserCache() {
			if (userCache == null) {
				userCache = getCacheManager().getCache("userCache");
			}
			return userCache;
		}
	
		public static Ehcache Initializer(URL url) {
			return null;
		}
	

}
