package com.example.threeshopping.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.MainActivity;


import com.example.threeshopping.particulars.ParticularsActivity;


public class AppModule {
    public static void init() {
        CommonArouter.getInstance().registerPath(Constants.PATH_MAIN, MainActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_PARTICULARS, ParticularsActivity.class);


    }
}
