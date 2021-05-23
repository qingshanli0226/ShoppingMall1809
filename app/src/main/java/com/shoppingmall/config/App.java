package com.shoppingmall.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shoppingmall.framework.manager.CacheManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
