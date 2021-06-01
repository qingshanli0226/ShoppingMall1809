package com.example.myapplication.app;

import android.app.Application;
import android.content.Intent;

import com.example.UserModule;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CaCheMannager;
import com.example.myapplication.AppModule;
import com.example.myapplication.MsgMoudel;
import com.example.myapplication.particulars.ParticularsModule;
import com.example.net.AppMoudel;
import com.example.user.AutoService;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppMoudel.init(this);
        UserModule.init();
        AppModule.init();
        ParticularsModule.init();
        MsgMoudel.init();
        CaCheMannager.getInstance().init();

        CaCheArote.getInstance().init(this);
    }
}
