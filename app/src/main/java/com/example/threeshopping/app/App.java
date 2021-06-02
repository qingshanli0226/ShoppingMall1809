package com.example.threeshopping.app;

import android.app.Application;


import com.example.framework.ShopCrashHandler;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheShopManager;
import com.example.message.module.MessageModule;
import com.example.net.module.NetModule;
import com.example.pay.module.PayModule;

import com.example.threeshopping.module.AppModule;
import com.example.user.module.UserModule;
import com.fiannce.sql.manager.SqlManager;
import com.tencent.bugly.crashreport.CrashReport;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonArouter.getInstance().init(this);
        AppModule.init();
        PayModule.init();
        MessageModule.init();
        UserModule.init();
        NetModule.context = this;

        CrashReport.initCrashReport(getApplicationContext(),"c6461cb8bf",true);//bugly

        SqlManager.getInstance().setContext(this);//数据库

        CacheShopManager.getInstance().init();//缓存购物车
        CacheAwaitPaymentManager.getInstance().init();

        CacheConnectManager.getInstance().init(this);//网络

        ShopCrashHandler.getInstance().init(this);//错误上报
//        ShopmallCrashHandler.getInstance().init(this);
 }
}
