package com.shoppingmall.framework.manager;

public class CacheManager {
    private static CacheManager cacheManager;

    public static CacheManager getInstance() {
        if (cacheManager==null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }
}