package com.example.shoppingmall1809.app;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.manager.GreenManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ShopmallCrashHandler;
import com.example.framework.manager.ShopManager;
import com.example.net.NetModule;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.init(this);

        NetModule.init(this);

        ShopManager.getInstance().init(this);

//        ShopmallCrashHandler.getInstance().init(this);

        ShoppingCarManager.getInstance().initLogin();

        GreenManager.getInstance().init(this);



        //友盟
        UMConfigure.init(this, "5fe2b40a842ba953b8948087", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "65eb88f36b08564266888ebe598de6ee");
//获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("wp","注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("wp","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

        //bugly
        CrashReport.initCrashReport(getApplicationContext(), "87da9e07da", false);

    }
}
