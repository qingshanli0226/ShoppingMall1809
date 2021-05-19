package com.shoppingmall.main.app;

import android.app.Application;

import com.fiance.user.UserModule;
import com.shoppingmall.AppModule;
import com.shoppingmall.framework.manager.FiannceArouter;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FiannceArouter.getInstance().init(this);
        UserModule.init();
        AppModule.init();
    }
}
