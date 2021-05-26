package com.shoppingmall.bawei.shoppingmall1809;

import android.app.Application;

import com.shoppingmall.bawei.shoppingmall1809.exception.ShopmallCrashHandler;

public class ShpmallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //应用一启动，就初始化应用的未捕获异常处理器
        ShopmallCrashHandler.getInstance().init(this);
    }
}
