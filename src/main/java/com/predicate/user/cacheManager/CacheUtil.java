package com.predicate.user.cacheManager;


import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:56 2018/5/21
 * @ModefiedBy:
 */
public class CacheUtil {


    private Cache cache;
    private EhCacheCacheManager cacheManager;

    public void setCacheManager(EhCacheCacheManager cacheManager) {
        this.cache = cacheManager.getCache("definedCache");

    }


    public void addDataIntoCache(String key, String value) {
        cache.put(key, key);

    }

    public String getDataFromCache(String key) {

        Cache.ValueWrapper wrapper = cache.get(key);
        return (String) wrapper.get();
    }

}
