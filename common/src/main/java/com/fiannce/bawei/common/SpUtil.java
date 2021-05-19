package com.fiannce.bawei.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    public static String getString(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("fiannceSp",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
