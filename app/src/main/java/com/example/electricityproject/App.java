package com.example.electricityproject;

import android.app.Application;
import android.content.Intent;

import com.example.common.NetModel;
import com.example.electricityproject.main.MainModel;
import com.example.framework.FrameModel;
import com.example.manager.CacheManger;
import com.example.user.UserModel;
import com.example.user.auto.AutoService;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetModel.init(this);
        FrameModel.init(this);
        UserModel.init();
        MainModel.init();

        CacheManger.getInstance().init(this);
        CacheManger.getInstance().registerUserManger();
        CacheManger.getInstance().registerBuyCarManger();

        startService(new Intent(this, AutoService.class));

    }
}
