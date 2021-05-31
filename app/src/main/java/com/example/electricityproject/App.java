package com.example.electricityproject;

import android.app.Application;
import android.content.Intent;

import com.example.common.NetModel;
import com.example.common.db.MessageDataBase;
import com.example.electricityproject.main.MainModel;
import com.example.framework.FrameModel;
import com.example.manager.BusinessNetManager;
import com.example.manager.ShopCacheManger;
import com.example.pay.order.PayModel;
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
        PayModel.init();

        //错误框架
//        ShopmallCrashHandler.getInstance().init(this);

        ShopCacheManger.getInstance().init(this);
        ShopCacheManger.getInstance().registerUserManger();
        ShopCacheManger.getInstance().registerBuyCarManger();

        //自动登录
        startService(new Intent(this, AutoService.class));
        //网络连接初始化
        BusinessNetManager.getInstance().init(this);

        //数据库
        MessageDataBase.getInstance().init(this);



    }
}
