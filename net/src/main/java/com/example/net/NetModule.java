package com.example.net;

import android.content.Context;

public class NetModule {
    public static void init(Context appcontext) {
        NetModule.context = appcontext;
    }

    public static Context context;

}
