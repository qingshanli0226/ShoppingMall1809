package com.example.threeshopping.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.MainActivity;
import com.example.threeshopping.welcome.WelActivity;

public class AppModule {
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_WEL, WelActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_MAIN, MainActivity.class);
    }
}
