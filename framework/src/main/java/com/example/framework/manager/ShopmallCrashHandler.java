package com.example.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import androidx.annotation.NonNull;

import com.example.net.RetrofitCreator;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * liqingshan 2021 5月26日
 */

//定义一个电商应用闪退的处理类，通过该类可以捕获到应用程序未处理的异常信息，捕获到之后，给用户提示，出现了什么错误，提升用户体验
//并且，将错误信息推送到服务端，便于开发人员获取这些信息，优化我们的应用.
public class ShopmallCrashHandler implements Thread.UncaughtExceptionHandler{
    private static ShopmallCrashHandler instance;
    private Thread.UncaughtExceptionHandler systemDefaultHandler;
    private Context applicationContext;
    private String crashPath = "/sdcard/shopmallcrash/";
    private ShopmallCrashHandler() {

    }

    public static ShopmallCrashHandler getInstance() {
        if (instance==null) {
            synchronized (ShopmallCrashHandler.class) {
                if (instance==null){
                    instance = new ShopmallCrashHandler();
                }
            }
        }
        return instance;
    }


    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
        systemDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();//先获取系统默认处理未捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(this);//把当前这个类作为未捕获异常的处理器

        //初始化存储错误的目录
        File crashFileDir = new File(crashPath);
        if (!crashFileDir.exists()) {
            crashFileDir.mkdir();
        }
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


    /**
     *
     * @param t 出现未捕获异常的线程
     * @param e 异常信息.当主线程出现未捕获异常，主线程的运行流程出现不可恢复的错误，应用无法正常运行，必须把应用杀掉重新启动。如果
     *          是子线程出现了不可捕获的异常，该线程将会出现不可恢复的错误。但是主线程正常
     */
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                //这个中间位置是运行在主线程的
                Toast.makeText(applicationContext,"对不起,程序运行出现了错误,请重新启动该应用!",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

        //将错误异常上报到服务端
        HashMap<String,String> param = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        param.put("message",stringWriter.toString());

        RetrofitCreator.getFiannceApiService().crashReport(param)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<CrashBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CrashBean crashBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        saveExecptionInfo(e);

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        //杀掉该进程，需要提前把所有activity全部finish掉才可以
        for(Activity activity:CacheManager.getInstance().getActivityList()) {
            activity.finish();
        }
        System.exit(1);//该方法可以杀掉进程


    }


}
