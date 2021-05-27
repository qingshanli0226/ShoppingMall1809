package com.shoppingmall.framework.manager;

import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.ShopCarBean;

public class CacheManager {
    private static CacheManager cacheManager;

    public static CacheManager getInstance() {
        if (cacheManager==null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    private HomeBean homeBean;
    private ShopCarBean shopCarBean;


    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public ShopCarBean getShopCarBean() {
        return shopCarBean;
    }

    public void setShopCarBean(ShopCarBean shopCarBean) {
        this.shopCarBean = shopCarBean;
    }
}

