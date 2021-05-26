package com.example.threeshopping.app;

import android.app.Activity;
import android.app.Application;


import androidx.viewpager.widget.ViewPager;

import com.example.common.LogUtil;
import com.example.common.ShopCrashHandler;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.CacheShopManager;
import com.example.net.module.NetModule;
import com.example.threeshopping.module.AppModule;
import com.example.user.module.UserModule;
import com.fiannce.sql.UtileSql;

import java.util.Locale;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;
import me.jessyan.autosize.utils.AutoSizeLog;
import retrofit2.http.HEAD;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonArouter.getInstance().init(this);
        AppModule.init();
        UserModule.init();
        NetModule.context = this;

        UtileSql.getInstance().setContext(this);

        CacheShopManager.getInstance().init();
        ShopCrashHandler.getInstance().init(this);

 }
}
