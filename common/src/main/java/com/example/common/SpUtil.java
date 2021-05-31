package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    public static String getString(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Constants.SP_TOKEN,"");
        return token;
    }

    public static void putString(Context context,String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences("autologin", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Constants.SP_TOKEN, token);
        edit.commit();
    }

}
