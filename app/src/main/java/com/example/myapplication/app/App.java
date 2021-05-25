package com.example.myapplication.app;

import android.app.Application;
import android.content.Intent;

import com.example.UserModule;
import com.example.framework.manager.CaCheArote;
import com.example.myapplication.AppModule;
import com.example.myapplication.MsgMoudel;
import com.example.myapplication.particulars.ParticularsModule;
import com.example.user.AutoService;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();



        UserModule.init();
        AppModule.init();
        ParticularsModule.init();
        MsgMoudel.init();

        CaCheArote.getInstance().init(this);

    }
}
