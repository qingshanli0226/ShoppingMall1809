package com.example.electricityproject;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.common.SPUtility;
import com.example.electricityproject.main.MainModel;
import com.example.framework.FrameModel;
import com.example.common.NetModel;
import com.example.user.UserModel;
import com.example.user.auto.AutoService;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetModel.init(this);
        FrameModel.init(this);
        UserModel.init();
        AppModel.init();
        MainModel.init();



    }
}
