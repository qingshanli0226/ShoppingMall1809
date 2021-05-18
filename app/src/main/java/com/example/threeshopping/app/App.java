package com.example.threeshopping.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.module.AppModule;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonArouter.getInstance().init(this);
        AppModule.init();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
