package com.example.net;


import com.example.common.Utility;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (Utility.getString(NetModel.context)!=null){
            chain.proceed(chain.request().newBuilder().addHeader("token",Utility.getString(NetModel.context)).build());
        }
        return chain.proceed(chain.request().newBuilder().addHeader("token","123").build());
    }
}
