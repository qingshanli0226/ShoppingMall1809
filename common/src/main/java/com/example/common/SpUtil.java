package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

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
    public static int getInt(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("readnum", Context.MODE_PRIVATE);
        int num = sharedPreferences.getInt(Constants.SP_READ_NUM,-1);
        return num;
    }

    public static void putInt(Context context,int num){
        SharedPreferences sharedPreferences = context.getSharedPreferences("readnum", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(Constants.SP_READ_NUM, num);
        edit.commit();
    }

    public static void putpath(Context context,String path){
        SharedPreferences sharedPreferences = context.getSharedPreferences("path", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Constants.SP_PATH, path);
        edit.commit();
    }

    public static String getpath(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("path", Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(Constants.SP_PATH, "http://inews.gtimg.com/newsapp_match/0/5131443473/0");
        return string;
    }

    public static void clean(Context context){
        SharedPreferences readnum = context.getSharedPreferences("readnum", Context.MODE_PRIVATE);
        SharedPreferences.Editor readnumedit = readnum.edit();
        readnumedit.clear();
        readnumedit.commit();
        SharedPreferences autologin = context.getSharedPreferences("autologin", Context.MODE_PRIVATE);
        SharedPreferences.Editor autologinedit = autologin.edit();
        autologinedit.clear();
        autologinedit.commit();
    }
}
