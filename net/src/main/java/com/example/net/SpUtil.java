package com.example.net;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String getString(Context context,String key){
        SharedPreferences finan = context.getSharedPreferences("finan", Context.MODE_PRIVATE);
        return finan.getString(key,"");
    }
    public static void setString(Context context,String key,String content){
        SharedPreferences finan = context.getSharedPreferences("finan", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = finan.edit();
        edit.putString(key,content);
        edit.commit();
    }
}
