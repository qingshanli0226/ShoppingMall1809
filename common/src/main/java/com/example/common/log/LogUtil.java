package com.example.common.log;

import android.util.Log;

import com.example.common.BuildConfig;

public class LogUtil {
    private static String className;
    private static String methodName;
    private static int lineNumber;
    private static final  String TAG ="shopmall1809";
    public static void v(String message) {
        if (BuildConfig.LOG_DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.v(TAG,className+""+methodName+""+lineNumber);
        }
    }
    public static void d(String message){
        if (BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.d(TAG,className+""+methodName+""+lineNumber);
        }
    }
    public static void i(String message){
        if (BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.i(TAG,className+""+methodName+""+lineNumber);
        }
    }
    public static void e(String message){
        if (BuildConfig.LOG_ERROR){
            createLogName(new Throwable().getStackTrace());
            Log.e(TAG,className+""+methodName+""+lineNumber);
        }
    }
    private static void createLogName(StackTraceElement[] stackTraceElements){
         className = stackTraceElements[1].getClassName();
         methodName = stackTraceElements[1].getMethodName();
         lineNumber = stackTraceElements[1].getLineNumber();
    }
}
