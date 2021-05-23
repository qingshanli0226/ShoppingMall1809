package com.example.user.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.user.user.UserActivity;

public class UserModule {
    public static void init() {
        CommonArouter.getInstance().registerPath(Constants.PATH_USER, UserActivity.class);
    }
}
