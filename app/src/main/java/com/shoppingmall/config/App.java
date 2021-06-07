package com.shoppingmall.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shoppingmall.detail.greendao.TableManager;
import com.shoppingmall.detail.messagedao.MessageManager;
import com.shoppingmall.framework.log.ShopCrashHandler;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.net.TokenInterceptor;
import com.shoppingmall.net.TokenInterceptorContext;
//import com.tencent.rtmp.TXLiveBase;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        TableManager.getInstance().init(this);
        TokenInterceptorContext.init(this);

        CacheShopManager.getInstance().init();
//        ShopCrashHandler.getInstance().init(this);
        MessageManager.getInstance().init(this);

//        String licenceURL = "http://license.vod2.myqcloud.com/license/v1/3f5243f79d66133baac62ff7e234b971/TXLiveSDK.licence"; // 获取到的 licence url
//        String licenceKey = "aea97dba59890ded890602f202adedfe"; // 获取到的 licence key
//        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
    }
}
