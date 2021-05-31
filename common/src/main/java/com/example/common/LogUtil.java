package com.example.common;

import android.util.Log;

public class LogUtil {
    private static String className;
    private static String methodName;
    private static int lineName;


    public static void v(String message){
        if(BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.v(""+className+""+methodName+""+lineName+"   ",message);
        }
    }

    public static void d(String message){
        if(BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.d(""+className+""+methodName+""+lineName+"   ",message);
        }
    }
    public static void i(String message){
        if(BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());

            Log.i(""+className+""+methodName+""+lineName+"   ",message);
        }
    }
    public static void e(String message){
        if(BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.e(""+className+""+methodName+""+lineName+"   ",message);
        }
    }

    private static void createLogName(StackTraceElement[] stackTrace) {
        className = stackTrace[1].getClassName();
        methodName = stackTrace[1].getMethodName();
        lineName = stackTrace[1].getLineNumber();
    }
}
