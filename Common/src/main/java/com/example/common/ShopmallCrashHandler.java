package com.example.common;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ShopmallCrashHandler implements Thread.UncaughtExceptionHandler{

    private static ShopmallCrashHandler shopmallCrashHandler;
    private Context context;
    private Thread.UncaughtExceptionHandler systemDefaultHandler;

    public static ShopmallCrashHandler getInstance() {
        if (shopmallCrashHandler == null) {
            synchronized (ShopmallCrashHandler.class){
                if (shopmallCrashHandler == null) {
                    shopmallCrashHandler = new ShopmallCrashHandler();
                }
            }
        }
        return shopmallCrashHandler;
    }

    public void init(Context application){
        this.context = application;
        systemDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "出现了一个意料之外的错，请重新启动", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        for (Activity activity: ActivityManger.getInstance().getActivity()) {
            activity.finish();
        }
        System.exit(1);

    }
}
