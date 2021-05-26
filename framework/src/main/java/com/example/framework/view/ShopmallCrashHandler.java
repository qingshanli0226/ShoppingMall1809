package com.example.framework.view;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.commom.LogUtils;
import com.example.framework.manager.CacheManager;
import com.example.net.RetrofitCreator;
import com.example.net.model.FindForBean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopmallCrashHandler implements Thread.UncaughtExceptionHandler {


    private Context applicationContext;
    private Thread.UncaughtExceptionHandler systemDefaultHandler;

    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
        systemDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        new Thread(() -> {
            Looper.prepare();
            Toast.makeText(applicationContext, "对不起，程序出现异常，请重新启动应用", Toast.LENGTH_SHORT).show();
            Looper.loop();
        }).start();


        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Activity activity : CacheManager.getInstance().getActivityList()) {
            activity.finish();
        }
        System.exit(1);

        HashMap<String, String> param = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        param.put("message",printWriter.toString());


        RetrofitCreator.getShopApiService().getCrash(param)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<FindForBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull FindForBean findForBean) {
                        LogUtils.d("上传错误成功"+findForBean.toString());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private static ShopmallCrashHandler shopmallCrashHandler;

    private ShopmallCrashHandler() {
    }

    public static ShopmallCrashHandler getInstance() {
        if (shopmallCrashHandler == null) {
            synchronized (ShopmallCrashHandler.class) {
                if (shopmallCrashHandler == null) {
                    shopmallCrashHandler = new ShopmallCrashHandler();
                }
            }
        }
        return shopmallCrashHandler;
    }

}
