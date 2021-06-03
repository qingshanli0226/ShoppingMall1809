package com.fiannce.live.module;

import android.provider.MediaStore;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.fiannce.live.live.LiveActivity;
import com.fiannce.live.video.VideoActivity;

public class LiveModule {
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_LIVE, LiveActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_VIDEO, VideoActivity.class);

    }
}
