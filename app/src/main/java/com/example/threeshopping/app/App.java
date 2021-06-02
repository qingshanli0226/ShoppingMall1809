package com.example.threeshopping.app;

import android.app.Application;
import android.app.Notification;
import android.content.Context;


import com.example.common.LogUtil;
import com.example.framework.ShopCrashHandler;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheMessageManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.CacheUserManager;
import com.example.message.module.MessageModule;
import com.example.net.module.NetModule;
import com.example.pay.module.PayModule;

import com.example.threeshopping.module.AppModule;
import com.example.user.module.UserModule;
import com.fiannce.sql.bean.MessageBean;
import com.fiannce.sql.manager.SqlManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;



import java.text.SimpleDateFormat;
import java.util.Map;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonArouter.getInstance().init(this);
        AppModule.init();
        PayModule.init();
        MessageModule.init();
        UserModule.init();
        NetModule.context = this;

        CrashReport.initCrashReport(getApplicationContext(),"c6461cb8bf",true);//bugly

        SqlManager.getInstance().init(this);


        CacheShopManager.getInstance().init();//缓存购物车

        CacheAwaitPaymentManager.getInstance().init();


        CacheMessageManager.getInstance().init(this);

        CacheConnectManager.getInstance().init(this);//网络

        ShopCrashHandler.getInstance().init(this);//错误上报
        //激光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        //友盟推送
        UMConfigure.init(this,"60b6d0e4bb989470aed55add"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"661d524b99751eaf6113b5f045d636e0");//
        PushAgent pushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        pushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken
                LogUtil.d("zyb"+deviceToken);
            }

            @Override
            public void onFailure(String s,String s1) {
            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                for (Map.Entry entry : msg.extra.entrySet()) {
                    MessageBean messageBean = new MessageBean();
                    messageBean.setId(null);
                    messageBean.setType(1);
                    messageBean.setMessage(entry.getValue()+"");
                    messageBean.setMessageTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(System.currentTimeMillis()));
                    messageBean.setIsRead(true);
                    CacheMessageManager.getInstance().addMessage(messageBean);
                }
                return super.getNotification(context, msg);
            }
        };
        pushAgent.setMessageHandler(messageHandler);
        //qq分享
//        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
 }
}
