package com.example.myapplication.app;

import android.app.Application;

import com.example.UserModule;
import com.example.framework.db.DbManager;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.MsgManager;
import com.example.myapplication.home.msg.MsgMoudel;
import com.example.framework.manager.PaySendCacheManager;


import com.example.myapplication.particulars.ParticularsModule;
import com.example.net.AppMoudel;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.rtmp.TXLiveBase;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppMoudel.init(this);
        UserModule.init();
        AppModule.init();
        ParticularsModule.init();
        MsgMoudel.init();
        CaCheMannager.getInstance().init();
        DbManager.getDbManager().init(this);
        MsgManager.getInstance().init();


        PaySendCacheManager.getInstance().init();
        CaCheArote.getInstance().init(this);
        UMConfigure.init(this, "5a12384aa40fa3551f0001d1"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
// 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
// QQ设置
        PlatformConfig.setQQZone("101830139", "5d63ae8858f1caab67715ccd6c18d7a5");
// 企业微信设置
// 新浪微博设置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
// 其他平台设置
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
        CrashReport.initCrashReport(getApplicationContext(),"7fee8d00b8",true);


        String licenceURL = "http://license.vod2.myqcloud.com/license/v1/b296bcad7e74aa354db14f649ef5dcb6/TXLiveSDK.licence"; // 获取到的 licence url
        String licenceKey = "6123f6cff979198c1eebded494e1f87e"; // 获取到的 licence key
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");
        CrashReport.initCrashReport(getApplicationContext(), "7fee8d00b8", true);
    }

}
