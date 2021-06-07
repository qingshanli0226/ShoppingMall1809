package com.example.threeshopping.app;

import android.app.Application;
import android.app.Notification;
import android.content.Context;


import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.common.LogUtil;
import com.example.framework.manager.CacheAddrManager;
import com.example.framework.manager.ShopCrashHandler;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheMessageManager;
import com.example.framework.manager.CacheShopManager;
import com.example.map.module.MapModule;
import com.example.message.module.MessageModule;
import com.example.net.module.NetModule;
import com.example.pay.module.PayModule;

import com.example.threeshopping.module.AppModule;
import com.example.user.module.UserModule;
import com.fiannce.live.module.LiveModule;
import com.fiannce.sql.bean.MessageBean;
import com.fiannce.sql.manager.SqlManager;
import com.tencent.bugly.crashreport.CrashReport;

import com.tencent.rtmp.TXLiveBase;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;


import java.text.SimpleDateFormat;
import java.util.Map;


import retrofit2.http.HEAD;



public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonArouter.getInstance().init(this);
        AppModule.init();
        PayModule.init();
        MapModule.init();
        LiveModule.init();
        MessageModule.init();
        UserModule.init();
        NetModule.context = this;

        CrashReport.initCrashReport(getApplicationContext(),"c6461cb8bf",true);//bugly

        SqlManager.getInstance().init(this);


        CacheShopManager.getInstance().init();//缓存购物车
        CacheAddrManager.getInstance().init();//地址信息
        CacheAwaitPaymentManager.getInstance().init();
        CacheMessageManager.getInstance().init(this);
        CacheConnectManager.getInstance().init(this);//网络

        //ShopCrashHandler.getInstance().init(this);//错误上报
        //激光推送
        //JPushInterface.setDebugMode(true);
        //JPushInterface.init(this);
        //友盟推送
        UMConfigure.init(this,"60b6d0e4bb989470aed55add"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"661d524b99751eaf6113b5f045d636e0");
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
        //qq分享
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
        //直播
        String licenceURL = "http://license.vod2.myqcloud.com/license/v1/74ca08e91d17f282d34a8d69f1d4dd37/TXLiveSDK.licence"; // 获取到的 licence url
        String licenceKey = "6e2da709f07cabf154638fbc8855725d"; // 获取到的 licence key
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
        //地图
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
 }
}
