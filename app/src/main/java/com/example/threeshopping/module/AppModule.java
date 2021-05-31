package com.example.threeshopping.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.MainActivity;


import com.example.threeshopping.bind.BindInfoActivity;
import com.example.threeshopping.particulars.ParticularsActivity;
import com.example.threeshopping.shopcart.ShopCartActivity;


public class AppModule {
    public static void init() {
        CommonArouter.getInstance().registerPath(Constants.PATH_MAIN, MainActivity.class);//主页面
        CommonArouter.getInstance().registerPath(Constants.PATH_PARTICULARS, ParticularsActivity.class);//详细页面
        CommonArouter.getInstance().registerPath(Constants.PATH_BIND, BindInfoActivity.class);//绑定地址
        CommonArouter.getInstance().registerPath(Constants.PATH_SHOPACTIVITY, ShopCartActivity.class);//购物车页面
    }
}
