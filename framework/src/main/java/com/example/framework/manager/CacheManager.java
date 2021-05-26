package com.example.framework.manager;

import android.app.Activity;

import com.example.commom.ShopConstants;
import com.example.net.model.HoemBean;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    public List<Activity> activityList = new LinkedList<>();

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void addActivityList(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivityList(Activity activity) {
        activityList.remove(activity);
    }

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
