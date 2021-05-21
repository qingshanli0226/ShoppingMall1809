package com.example.common;


import android.content.Context;
import android.content.SharedPreferences;

public class SPUtility {
    private static SPUtility utility;

    public static SPUtility getInstance() {
        if (utility ==null){
            utility =new SPUtility();
        }
        return utility;
    }
    public static String getString(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("auto", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        return token;
    }
    public static void putString(Context context,String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences("auto", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("token",token);
        edit.commit();
    }
}
