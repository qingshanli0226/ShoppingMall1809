package com.shoppingmall.net.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.shoppingmall.net.Constants;

public class SpUtil {
    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public static void putString(Context context,String key,String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,token);
        edit.commit();
    }

}
