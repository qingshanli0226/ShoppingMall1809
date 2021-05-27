package com.example.threeshopping.exception;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.LogUtil;
import com.example.framework.manager.CacheShopManager;

public class ShopmallCrashHandler implements Thread.UncaughtExceptionHandler{
    private static ShopmallCrashHandler instance;
    private Thread.UncaughtExceptionHandler systemDefaultHandler;
    private Context applicationContext;
    private ShopmallCrashHandler(){

    }
    public static  ShopmallCrashHandler getInstance(){
        if (instance == null){
            synchronized (ShopmallCrashHandler.class){
                if (instance == null){
                    instance = new ShopmallCrashHandler();
                }
            }
        }
        return instance;
    }

    public void init (Context applicationContext){
        this.applicationContext = applicationContext;
        systemDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                //中间运行在主线程

                Toast.makeText(applicationContext, "出现了一个意料之外的错误，请重新启动", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        LogUtils.json(e);
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
//        for (Activity activity: CacheShopManager.getInstance().getActivityList()
//             ) {
//            activity.finish();
//        }
//        System.exit(1);
    }
}
