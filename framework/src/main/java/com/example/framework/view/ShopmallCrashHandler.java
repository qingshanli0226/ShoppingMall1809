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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopmallCrashHandler implements Thread.UncaughtExceptionHandler {


    private Context applicationContext;
    private Thread.UncaughtExceptionHandler systemDefaultHandler;
    private String crashPath = "/sdcard/shopmallcrash/";

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

        saveExecptionInfo(throwable);
    }


    private void saveExecptionInfo(Throwable e) {
        //将异常信息放到一个输出流里
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        //生成一个文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = simpleDateFormat.format(new Date());

        File crashFile = new File(crashPath+timeStr+".txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(crashFile);
            String crashStr = stringWriter.toString();
            byte[] crashByteArray = crashStr.getBytes();
            int length = crashByteArray.length;
            try {
                fileOutputStream.write(crashByteArray, 0, length);
                fileOutputStream.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

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
