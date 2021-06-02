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
import com.umeng.socialize.PlatformConfig;

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
        UMConfigure.setLogEnabled(true);
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

        // 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWXFileProvider("com.tencent.sample2.fileprovider");
// QQ设置
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");
// 企业微信设置
        PlatformConfig.setWXWork("wwac6ffb259ff6f66a","EU1LRsWC5uWn6KUuYOiWUpkoH45eOA0yH-ngL8579zs","1000002","wwauthac6ffb259ff6f66a000002");
        PlatformConfig.setWXWorkFileProvider("com.tencent.sample2.fileprovider");
// 新浪微博设置
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setSinaFileProvider("com.tencent.sample2.fileprovider");
// 其他平台设置
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4","d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi","MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
//        PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");
        //bugly
        CrashReport.initCrashReport(getApplicationContext(), "87da9e07da", false);

    }
}
