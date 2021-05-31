package com.example.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManger {

    private static ActivityManger activityManger;
    private List<Activity> activityList = new ArrayList<>();

    public static synchronized ActivityManger getInstance() {
        if (activityManger==null){
            activityManger = new ActivityManger();
        }
        return activityManger;
    }

    public void register(Activity activity){
        activityList.add(activity);
    }

    public void unregister(Activity activity){
        activityList.remove(activity);
    }

    public List<Activity> getActivity(){
        return activityList;
    }



}
