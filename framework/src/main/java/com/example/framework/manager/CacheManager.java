package com.example.framework.manager;

import com.example.commom.ShopConstants;
import com.example.net.model.HoemBean;

public class CacheManager {
    public String decideARoutPage = "";

    private HoemBean hoemBean;

    public HoemBean getHoemBean() {
        return hoemBean;
    }

    public void setHoemBean(HoemBean hoemBean) {
        this.hoemBean = hoemBean;
    }


    private static CacheManager simpleManager;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (simpleManager == null) {
            simpleManager = new CacheManager();
        }
        return simpleManager;
    }

}
