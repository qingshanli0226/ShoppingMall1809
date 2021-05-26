package com.example.common;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ShopCrashHandler implements Thread.UncaughtExceptionHandler {
    private static ShopCrashHandler instance;
    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private ShopCrashHandler() {
    }

    public static ShopCrashHandler getInstance() {
        if (instance == null) {
            synchronized (ShopCrashHandler.class) {
                if (instance == null) {
                    instance = new ShopCrashHandler();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();//获取系统默认处理未捕获异常
        Thread.setDefaultUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        LogUtil.e("zyb:"+e.getMessage());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "对不起程序错误，请重新启动服务", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }



    }
}
