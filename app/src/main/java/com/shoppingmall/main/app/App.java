package com.shoppingmall.main.app;

import android.app.Application;

import com.fiance.user.UserModule;
import com.shoppingmall.config.AppModule;
import com.shoppingmall.framework.manager.ShopMallArouter;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShopMallArouter.getInstance().init(this);
        UserModule.init();
        AppModule.init();
    }
}
