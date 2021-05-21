package com.example.net;


import android.util.Log;

import com.example.common.NetModel;
import com.example.common.SPUtility;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
            Log.i("zx", "intercept: "+SPUtility.getString(NetModel.context));
            return chain.proceed(chain.request().newBuilder().addHeader("token", SPUtility.getString(NetModel.context)).build());
    }
    //4e53b37b-38aa-4885-b386-573f52bdfdafAND1621550495929
}
