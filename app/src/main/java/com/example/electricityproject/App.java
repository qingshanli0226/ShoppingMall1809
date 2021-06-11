package com.example.electricityproject;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.common.NetModel;
import com.example.common.db.MessageDataBase;
import com.example.electricityproject.main.MainModel;
import com.example.electricityproject.shopp.userinfo.infodb.DaoMaster;
import com.example.electricityproject.shopp.userinfo.infodb.UserInfoTableManger;
import com.example.framework.FrameModel;
import com.example.manager.BusinessNetManager;
import com.example.manager.SPMessageNum;
import com.example.manager.ShopCacheManger;
import com.example.pay.order.PayModel;
import com.example.user.UserModel;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        NetModel.init(this);
        FrameModel.init(this);
        UserModel.init();
        MainModel.init();
        PayModel.init();
        //项目开始前把友盟数据信息从数据库中保存到缓存类中

        //信息数据库
        MessageDataBase.getInstance().init(this);
        //创建数据库
        DaoMaster daoMaster = UserInfoTableManger.getInstance().getDaoMaster(this);

        //错误框架
//        ShopmallCrashHandler.getInstance().init(this);

        ShopCacheManger.getInstance().init(this);

        //自动登录
//        startService(new Intent(this, AutoService.class));
        //网络连接初始化
        BusinessNetManager.getInstance().init(this);

        //查询数据库 把信息数据库的数量存储到SP
        SPMessageNum.getInstance().init(this);

        PushAgent mPushAgent = PushAgent.getInstance(this);

//        //腾讯云拉流
//        String licenceURL =
//                "http://license.vod2.myqcloud.com/license/v1/5f6b6f8c1bb8c3fd3a33817371f486ec/TXLiveSDK.licence"; // 获取到的 licence url
//        String licenceKey =
//                "a37f11535e6807594a27d970c72f10b7"; // 获取到的 licence key
        UMConfigure.init(this, "60b8aa4085c1f6195c4739a1", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "22702403bca24d3c80567a517d3b5966");
        String licenceURL =
                "http://license.vod2.myqcloud.com/license/v1/5f6b6f8c1bb8c3fd3a33817371f486ec/TXLiveSDK.licence"; // 获取到的 licence url
        String licenceKey =
                "a37f11535e6807594a27d970c72f10b7"; // 获取到的 licence key
//        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);

        //友盟分享以及推送
        UMConfigure.init(this, "60b75be07825f22147696d55", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "55e79a9aed800a28bc2d246014ac345b");
        //获取消息推送代理示例
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


//        UMConfigure.init(this,"60b622d96c421a3d97d78a5b"
//                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"469add7072c102fc925c742f7277e61d");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
// 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0");
// QQ设置
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
// 企业微信设置
// 新浪微博设置
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
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

        //bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "c66d73003a", true);

        //百度地图
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);


        //内存泄漏
        LeakCanary.install(this);

    }
}
