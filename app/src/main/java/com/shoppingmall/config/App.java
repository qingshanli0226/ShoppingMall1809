package com.shoppingmall.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shoppingmall.detail.greendao.TableManager;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.net.TokenInterceptor;
import com.shoppingmall.net.TokenInterceptorContext;
import com.yoho.greendao.gen.DaoSession;

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
    }
}
