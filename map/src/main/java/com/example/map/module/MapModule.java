package com.example.map.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.map.checklog.CheckLogActivity;

public class MapModule {
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_BIND, CheckLogActivity.class);
    }
}
