package com.example.electricityproject;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.common.NetModel;
import com.example.common.db.MessageDataBase;
import com.example.electricityproject.main.MainModel;
import com.example.framework.FrameModel;
import com.example.manager.BusinessNetManager;
import com.example.manager.SPMessageNum;
import com.example.manager.ShopCacheManger;
import com.example.pay.order.PayModel;
import com.example.user.UserModel;
import com.example.user.auto.AutoService;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

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

        //查询数据库 把数据库的数量存储到SP
        SPMessageNum.getInstance().init(this);


        UMConfigure.init(this, "60b6d467f9248230bbc40432", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "57c70318fff23a7b1121c428b9c6a502");
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("zx","注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("zx","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }
}
