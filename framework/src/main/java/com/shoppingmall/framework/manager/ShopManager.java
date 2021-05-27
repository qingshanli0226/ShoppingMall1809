package com.shoppingmall.framework.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ShopManager {
    private static ShopManager shopManager;
    private List<Activity> activities = new ArrayList<>();

    public ShopManager() {
    }

    public static ShopManager getInstance() {
        if (shopManager == null){
            shopManager = new ShopManager();
        }
        return shopManager;
    }

    public void register(Activity activity){
        activities.add(activity);
    }
    public void unRegister(Activity activity){
        activities.remove(activity);
    }

    public List<Activity> getActivities(){
        return activities;
    }
}
