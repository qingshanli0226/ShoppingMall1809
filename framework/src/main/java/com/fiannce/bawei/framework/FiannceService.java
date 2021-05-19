package com.fiannce.bawei.framework;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.fiannce.bawei.framework.manager.FiannceUserManager;

import java.util.List;

import androidx.annotation.NonNull;

public class FiannceService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FiannceUserManager.getInstance().setLogin(true);
                handler.sendEmptyMessage(1);
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            isApplicationUsed();
        }
    };

    private boolean isApplicationUsed() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        //Androi系统里所有的应用都被ActivityMananger管理，它可以获取所有的应用列表
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        //遍历应用列表，比较每个应用程序包名和我们应用的包名是否一致，如果一致就代表找到了我们的应用
        for(ActivityManager.RunningAppProcessInfo runningAppProcessInfo:runningAppProcessInfoList) {
            if (runningAppProcessInfo.processName.equals(getPackageName())) {
                //判断当前我们的应用是否是前台进程，如果是代表着用户正在操作我们的应用
                if (runningAppProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.d("LQS", "是否在前台" + true);
                    return true;
                } else {
                    Log.d("LQS", "是否在前台" + false);
                    return false;
                }
            }
        }
        return false;
    }
}
