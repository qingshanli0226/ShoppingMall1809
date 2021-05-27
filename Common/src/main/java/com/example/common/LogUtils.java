package com.example.common;

import android.util.Log;

public class LogUtils {

    public static void v(String message){
        if (BuildConfig.LOG_DEBUG){
            Log.v("FOUR",message);
        }
    }

    public static void d(String message){
        if (BuildConfig.LOG_DEBUG){
            Log.v("FOUR",message);
        }
    }

    public static void i(String message){
        if (BuildConfig.LOG_DEBUG){
            Log.v("FOUR",message);
        }
    }

    public static void e(String message){
        if (BuildConfig.LOG_DEBUG){
            Log.v("FOUR",message);
        }
    }

}
