package com.fiance.commom;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("shopmallSp", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void setString(Context context,String key){
        SharedPreferences shopmallSp = context.getSharedPreferences("shopmallSp", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = shopmallSp.edit();

        edit.putString(ShopmallConstants.TOKEN_KEY,key);

        edit.commit();
    }
}
