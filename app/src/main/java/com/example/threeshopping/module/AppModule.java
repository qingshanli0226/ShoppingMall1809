package com.example.threeshopping.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.MainActivity;


import com.example.threeshopping.bind.BindInfoActivity;
import com.example.threeshopping.particulars.ParticularsActivity;
import com.example.threeshopping.shopcart.ShopCartActivity;


public class AppModule {
    public static void init() {
        CommonArouter.getInstance().registerPath(Constants.PATH_MAIN, MainActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_PARTICULARS, ParticularsActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_PARTICULARS, BindInfoActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_SHOPACTIVITY, ShopCartActivity.class);
    }
}
