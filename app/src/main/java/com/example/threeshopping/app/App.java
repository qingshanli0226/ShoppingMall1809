package com.example.threeshopping.app;

import android.app.Application;


import com.example.common.module.CommonArouter;
import com.example.net.module.NetModule;
import com.example.threeshopping.module.AppModule;
import com.example.user.module.UserModule;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonArouter.getInstance().init(this);
        AppModule.init();
        UserModule.init();
        NetModule.context = this;

    }
}
