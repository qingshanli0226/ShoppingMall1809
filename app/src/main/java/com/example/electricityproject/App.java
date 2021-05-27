package com.example.electricityproject;

import android.app.Application;
import android.content.Intent;

import com.example.common.NetModel;
import com.example.electricityproject.main.MainModel;
import com.example.framework.FrameModel;
import com.example.manager.ShopCacheManger;
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

        ShopCacheManger.getInstance().init(this);
        ShopCacheManger.getInstance().registerUserManger();
        ShopCacheManger.getInstance().registerBuyCarManger();

            startService(new Intent(this, AutoService.class));

<<<<<<< HEAD
        startService(new Intent(this, AutoService.class));
=======
>>>>>>> 93e18fd45699267a6cb57c6df0461a26e1180947

    }
}
