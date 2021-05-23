package com.example.framework.manager;


import com.example.net.bean.HomeBean;

public class CacheHomeManager {
    private static CacheHomeManager cacheManager;

    private CacheHomeManager() {
    }

    public synchronized static CacheHomeManager getInstance(){
        if (cacheManager == null) {
            cacheManager = new CacheHomeManager();
        }
        return cacheManager;
    }

    private HomeBean homeBean;



    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
