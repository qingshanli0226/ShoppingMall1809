package com.example.commom;

import android.util.Log;

public class LogUtils {

    private static final String TAG = "weipei";
    private static String className;
    private static String methodName;
    private static int lineNumber;

    public static void v(String manager) {
        if (BuildConfig.DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.v(TAG, className + "   " + methodName + "   " + lineNumber + "   " + manager);
        }
    }

    public static void d(String manager) {
        if (BuildConfig.DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.d(TAG, className + "   " + methodName + "   " + lineNumber + "   " + manager);
        }
    }

    public static void i(String manager) {
        if (BuildConfig.DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.i(TAG, className + "   " + methodName + "   " + lineNumber + "   " + manager);
        }
    }

    public static void e(String manager) {
        if (BuildConfig.DEBUG) {
            createLogName(new Throwable().getStackTrace());
            Log.e(TAG, className + "   " + methodName + "   " + lineNumber + "   " + manager);
        }
    }

    private static void createLogName(StackTraceElement[] stackTrace) {
        className = stackTrace[1].getClassName();
        methodName = stackTrace[1].getMethodName();
        lineNumber = stackTrace[1].getLineNumber();
    }

}
