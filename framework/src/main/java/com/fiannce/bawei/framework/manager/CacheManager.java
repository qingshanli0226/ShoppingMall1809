package com.fiannce.bawei.framework.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.fiannce.bawei.framework.R;
import com.fiannce.bawei.net.mode.HomeBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;

public class CacheManager {

    private static CacheManager instance;
    private HomeBean homeBean;

    public List<Bitmap> bitmapList = new ArrayList<>();//bitmapList是成员变量


    private CacheManager() {
    }

    public void init(Context context) {

            for(int i = 0; i < 50; i++) {
                Log.d("LQS", " i = " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), com.fiannce.bawei.framework.R.mipmap.e);
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


    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
