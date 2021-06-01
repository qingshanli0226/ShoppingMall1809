package com.example.shoppingmallsix.app;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.NetworkConnectionsManager;
import com.example.framework.manager.ShopmallCrashHandler;
import com.example.net.module.NetModule;
import com.example.user.service.AutoService;
import com.tencent.bugly.crashreport.CrashReport;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "58e695ca1b", true);
        ARouter.init(this);
        NetModule.init(this);
        CacheConnectManager.getInstance().init(this);
        startService(new Intent(this, AutoService.class));
//        ShopmallCrashHandler.getInstance().init(this);
        NetworkConnectionsManager.getInstance().init(this);
    }
}
