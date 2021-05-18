package com.example.electricityproject;

import android.app.Application;

import com.example.common.call.BusinessARouter;
import com.example.framework.FrameModel;
import com.example.net.NetModel;
import com.example.user.UserModel;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetModel.init(this);
        FrameModel.init(this);
        UserModel.init();

    }
}
