package com.example.common;

import android.content.Context;

public class ComContext {
    private static ComContext manage;
    public static ComContext getInstance() {
        if (manage==null){
            manage=new ComContext();
        }
        return manage;
    }
    public Context context;

    public void init(Context mContext){
        this.context=mContext;
    }

    public Context getContext() {
        return context;
    }
}
