package com.example.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.framework.R;
import com.example.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Cache;

public class CacheManager {

    private static CacheManager instance;
    private HomeBean homeBean;

    public List<Bitmap> bitmapList = new ArrayList<>();//bitmapList是成员变量

    private List<Activity> activityList = new LinkedList<>();


    public CacheManager() {
    }

    public void init(Context context) {

            for(int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.save);
                bitmapList.add(bitmap);
            }

    }

    //多线程去实例化该实例
    public  static synchronized CacheManager getInstance() {
        if (instance==null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public List<Activity> getActivityList() {
        return activityList;
    }


    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
