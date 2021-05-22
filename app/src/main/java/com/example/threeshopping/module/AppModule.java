package com.example.threeshopping.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.MainActivity;
import com.example.threeshopping.particulars.ParticularsActivity;
import com.example.threeshopping.welcome.WelActivity;
import com.example.user.login.LoginActivity;
import com.example.user.register.RegisterActivity;

public class AppModule {
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_WEL, WelActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_MAIN, MainActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_LOGIN, LoginActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_REGISTER, RegisterActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_PARTICULARS, ParticularsActivity.class);
    }


}
