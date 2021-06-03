package com.example.shoppingmallsix.app;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.MessageManager;
import com.example.framework.manager.NetworkConnectionsManager;
import com.example.framework.manager.ShopmallCrashHandler;
import com.example.net.module.NetModule;
import com.example.shoppingcar.BuildConfig;
import com.example.user.service.AutoService;
import com.tencent.bugly.crashreport.CrashReport;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

public class  App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "58e695ca1b", true);
        ARouter.init(this);
        NetModule.init(this);
        CacheConnectManager.getInstance().init(this);
        startService(new Intent(this, AutoService.class));


        ShopmallCrashHandler.getInstance().init(this);

        UMConfigure.init(this, "604856545fc49d4ed0fb0923", "DEF", UMConfigure.DEVICE_TYPE_PHONE,
                "f2646d397f5c26a26c21a35684039292");
        UMConfigure.setLogEnabled(true);

        initUmengPush();
            UMConfigure.init(this,"604856545fc49d4ed0fb0923"
                    ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"f2646d397f5c26a26c21a35684039292");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0



        // 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0");
       // PlatformConfig.setWXFileProvider("com.tencent.sample2.fileprovider");
// QQ设置
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
       // PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");
// 企业微信设置
      //  PlatformConfig.setWXWork("wwac6ffb259ff6f66a","EU1LRsWC5uWn6KUuYOiWUpkoH45eOA0yH-ngL8579zs","1000002","wwauthac6ffb259ff6f66a000002");
      //  PlatformConfig.setWXWorkFileProvider("com.tencent.sample2.fileprovider");
// 新浪微博设置
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
      //  PlatformConfig.setSinaFileProvider("com.tencent.sample2.fileprovider");
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
      //  PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");



        CacheManager.getInstance().init(this);
        MessageManager.getInstance().init(this);

        NetworkConnectionsManager.getInstance().init(this);

    }


    /**
     * umeng push
     */
    private void initUmengPush() {
        PushAgent pushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调⽤register⽅法都会回调该接⼝
        pushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                LogUtils.d(deviceToken);
                if (BuildConfig.DEBUG) Log.d("App", deviceToken);
                Log.d("wqq", "onSuccess: "+deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {
                LogUtils.e(s + "----" + s1);
            }
        });
        //⾃定义通知样式
// pushAgent.setMessageHandler(new UmengMessageHandler() {
//
// @Override
// public Notification getNotification(Context context, UMessage msg) {
// if (msg.builder_id == 1) {
// Notification.Builder builder = new Notification.Builder(context);
// RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_view);
// remoteViews.setTextViewText(R.id.notification_title, msg.title);
// remoteViews.setTextViewText(R.id.notification_text, msg.text);
// remoteViews.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
// remoteViews.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
// builder.setContent(remoteViews)
// .setSmallIcon(getSmallIconId(context, msg))
// .setTicker(msg.ticker)
// .setAutoCancel(true);
// return builder.getNotification();
// }
// //默认为0，若填写的builder_id并不存在，也使⽤默认。
// return super.getNotification(context, msg);
// }
// });
    }



}







