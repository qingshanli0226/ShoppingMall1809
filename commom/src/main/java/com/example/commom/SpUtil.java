package com.example.commom;

import android.content.Context;
import android.content.SharedPreferences;


public class SpUtil {
    public static String getString(Context context, String key) {
        SharedPreferences fiannceSp = context.getSharedPreferences(ShopConstants.SP_FIANNCE, Context.MODE_PRIVATE);
        return fiannceSp.getString(key, "");
    }


    public static void setString(Context context, String key, String mag) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ShopConstants.SP_FIANNCE, Context.MODE_PRIVATE).edit();
        editor.putString(key, mag);
        editor.commit();
    }


    public static boolean getUpdateApk(Context context, String key) {
        SharedPreferences fiannceSp = context.getSharedPreferences(ShopConstants.SP_FIANNCE, Context.MODE_PRIVATE);
        return fiannceSp.getBoolean(key, false);
    }


    public static void setUpdateApk(Context context, String key, boolean b) {
        SharedPreferences.Editor edit = context.getSharedPreferences(ShopConstants.SP_FIANNCE, Context.MODE_PRIVATE).edit();
        edit.putBoolean(key, b);
        edit.commit();
    }


}
