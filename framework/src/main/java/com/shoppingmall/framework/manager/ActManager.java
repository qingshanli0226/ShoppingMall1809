package com.shoppingmall.framework.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActManager {
    private static ActManager actManager;
    private List<Activity> activities = new ArrayList<>();

    private ActManager() {
    }

    public static ActManager getActManager() {
        if (actManager == null) {
            actManager = new ActManager();
        }
        return actManager;
    }

    public void register(Activity activity){
        activities.add(activity);
    }

    public void unRegister(Activity activity){
        activities.remove(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
