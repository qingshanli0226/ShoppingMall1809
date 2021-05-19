package com.example.commened;

import android.content.Context;
import android.content.SharedPreferences;


public class SpUtil {


    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FiannceContants.TOKEN_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void putString(Context context, String key, String content) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FiannceContants.TOKEN_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, content);
        edit.commit();
    }

}
