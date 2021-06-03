package com.example.electricityproject;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

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
import com.example.user.auto.AutoService;
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

        //创建数据库
        DaoMaster daoMaster = UserInfoTableManger.getInstance().getDaoMaster(this);


        //错误框架
//        ShopmallCrashHandler.getInstance().init(this);


        ShopCacheManger.getInstance().init(this);
//        ShopCacheManger.getInstance().registerUserManger();
//        ShopCacheManger.getInstance().registerBuyCarManger();

        //自动登录
        startService(new Intent(this, AutoService.class));
        //网络连接初始化
        BusinessNetManager.getInstance().init(this);

        //数据库
        MessageDataBase.getInstance().init(this);

        //查询数据库 把数据库的数量存储到SP
        SPMessageNum.getInstance().init(this);


        String licenceURL =
                "http://license.vod2.myqcloud.com/license/v1/5f6b6f8c1bb8c3fd3a33817371f486ec/TXLiveSDK.licence"; // 获取到的 licence url
        String licenceKey =
                "a37f11535e6807594a27d970c72f10b7"; // 获取到的 licence key
//        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);

        UMConfigure.init(this, "60b75be07825f22147696d55", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "55e79a9aed800a28bc2d246014ac345b");
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
//        UMConfigure.init(this,"60b622d96c421a3d97d78a5b"
//                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"469add7072c102fc925c742f7277e61d");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
// 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0");
//        PlatformConfig.setWXFileProvider("com.tencent.sample2.fileprovider");
// QQ设置
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
//        PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");
// 企业微信设置
//        PlatformConfig.setWXWork("wwac6ffb259ff6f66a","EU1LRsWC5uWn6KUuYOiWUpkoH45eOA0yH-ngL8579zs","1000002","wwauthac6ffb259ff6f66a000002");
//        PlatformConfig.setWXWorkFileProvider("com.tencent.sample2.fileprovider");
// 新浪微博设置
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
//        PlatformConfig.setSinaFileProvider("com.tencent.sample2.fileprovider");
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
    }
}
