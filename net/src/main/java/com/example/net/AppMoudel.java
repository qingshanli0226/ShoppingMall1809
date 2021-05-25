package com.example.net;

import android.content.Context;

public class AppMoudel {
    public static void init(Context applicatin){
        context=applicatin;
    }
    public static Context context;

    public static Context getContext() {
        return context;
    }

}
