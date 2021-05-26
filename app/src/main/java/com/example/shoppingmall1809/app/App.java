package com.example.shoppingmall1809.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ShopmallCrashHandler;
import com.example.framework.manager.ShopManager;
import com.example.net.NetModule;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.init(this);

        NetModule.init(this);

        ShopManager.getInstance().init(this);

//        ShopmallCrashHandler.getInstance().init(this);

        ShoppingCarManager.getInstance().initLogin();
    }
}
