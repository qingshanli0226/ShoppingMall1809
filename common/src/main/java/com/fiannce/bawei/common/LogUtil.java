package com.fiannce.bawei.common;

import android.util.Log;

public class LogUtil {
    private static String className;
    private static String methodName;
    private static int lineName;
    private static final String TAG = "Shopmall18809";

    public static void v(String message) {
        if (BuildConfig.LOG_DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.v(TAG, className + " " + methodName + " " + lineName);
        }
    }


    public static void d(String message) {
        if (BuildConfig.LOG_DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.d(TAG, "" + className + " " + methodName + " " + lineName + " " + message);
        }
    }
    public static void i(String message) {
        if (BuildConfig.LOG_DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.i(TAG, className + " " + methodName + " " + lineName);
        }
    }
    public static void e(String message) {
        if (BuildConfig.LOG_ERROR) {
            createLogName(new Throwable().getStackTrace());
            Log.e(TAG, className + " " + methodName + " " + lineName);
        }
    }

    private static void createLogName(StackTraceElement[] stackTraceElements) {
        className = stackTraceElements[1].getClassName();//获取调用该log的类名，里面含有包名
        methodName = stackTraceElements[1].getMethodName();//获取调用该log的方法名
        lineName = stackTraceElements[1].getLineNumber();//获取调用该log的行数值
    }

}
