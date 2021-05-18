package com.example.framework;

import android.content.Context;

public class FrameModel {
    public static Context context;
    public static void init(Context application){
        context = application;
    }
}
