package com.example.shoppingmall1809.app;

import android.app.Application;
import android.os.UserManager;

import com.alibaba.android.arouter.launcher.ARouter;
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

    }
}
