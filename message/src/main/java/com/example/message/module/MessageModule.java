package com.example.message.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.message.MessageActivity;

public class MessageModule {
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_MESSAGE, MessageActivity.class);

    }
}
