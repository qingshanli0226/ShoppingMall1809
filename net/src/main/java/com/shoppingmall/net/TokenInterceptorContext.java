package com.shoppingmall.net;

import android.content.Context;

public class TokenInterceptorContext {
    public  static Context context;
    public static void init(Context appContext){
        context=appContext;
    }
}
