package com.example.threeshopping.app;

import android.app.Application;


import com.example.framework.ShopCrashHandler;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.CacheShopManager;
import com.example.message.module.MessageModule;
import com.example.net.module.NetModule;
import com.example.pay.module.PayModule;

import com.example.threeshopping.module.AppModule;
import com.example.user.module.UserModule;
import com.fiannce.sql.manager.SqlManager;

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

        SqlManager.getInstance().setContext(this);

        CacheShopManager.getInstance().init();

        ShopCrashHandler.getInstance().init(this);

//        ShopmallCrashHandler.getInstance().init(this);
 }
}
