package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    public static Boolean getSelectAllBol(Context context){
        SharedPreferences info = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        boolean is_select = info.getBoolean("is_select", false);
        return is_select;
    }

    public static void putSelectAllBol(Context context,Boolean is_select){
        SharedPreferences info = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = info.edit();
        edit.putBoolean("is_select",is_select);
        edit.commit();
    }

    public static void putTime(Context context,String time){
        SharedPreferences info = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = info.edit();
        edit.putString("time",time);
        edit.commit();
    }

    public static String getTime(Context context){
        SharedPreferences info = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        String time = info.getString("time", "");
        return time;
    }
}
