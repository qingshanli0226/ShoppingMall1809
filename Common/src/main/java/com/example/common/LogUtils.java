package com.example.common;

import android.util.Log;

public class LogUtils {

    private static String className;
    private static String methodName;
    private static int linName;

    public static void v(String message){
        if (BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.v("FOUR","  "+ className + "类  " + methodName + "方法  " + linName + "行  " + message);
        }
    }



    public static void d(String message){
        if (BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.d("FOUR","  "+ className + "类  " + methodName + "方法  " + linName + "行  " + message);

        }
    }

    public static void i(String message){
        if (BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.i("FOUR","  "+ className + "类  " + methodName + "方法  " + linName + "行  " + message);

        }
    }

    public static void e(String message){
        if (BuildConfig.LOG_DEBUG){
            createLogName(new Throwable().getStackTrace());
            Log.e("FOUR","  "+ className + "类  " + methodName + "方法  " + linName + "行  " + message);
        }
    }

    private static void createLogName(StackTraceElement[] stackTraceElements) {
        className = stackTraceElements[1].getClassName(); //获取调用log的类名
        methodName = stackTraceElements[1].getMethodName();  //获取方法名
        linName = stackTraceElements[1].getLineNumber();  //获取行数
    }

}
