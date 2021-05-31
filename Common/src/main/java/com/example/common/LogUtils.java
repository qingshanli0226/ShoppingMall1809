package com.example.common;

import android.util.Log;

public class LogUtils {

    public static void v(String Class , int lin , String message){
        if (BuildConfig.LOG_DEBUG){
            Log.v("FOUR","  "+ Class + "  " + lin + "行   " + message);
        }
    }

    public static void d(String Class , int lin , String message){
        if (BuildConfig.LOG_DEBUG){
            Log.d("FOUR","  "+ Class + "  " + lin + "行   " + message);
        }
    }

    public static void i(String Class , int lin , String message){
        if (BuildConfig.LOG_DEBUG){
            Log.i("FOUR","  "+ Class + "  " + lin + "行   " + message);
        }
    }

    public static void e(String Class , int lin , String message){
        if (BuildConfig.LOG_DEBUG){
            Log.e("FOUR","  "+ Class + "  " + lin + "行   " + message);
        }
    }

}
