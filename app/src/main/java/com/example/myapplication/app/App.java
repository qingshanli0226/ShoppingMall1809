package com.example.myapplication.app;

import android.app.Application;

import com.example.UserModule;
import com.example.framework.manager.CaCheArote;
import com.example.myapplication.AppModule;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UserModule.init();
        AppModule.init();
        CaCheArote.getInstance().init(this);
    }
}
