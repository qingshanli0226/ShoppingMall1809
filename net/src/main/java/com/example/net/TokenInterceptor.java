package com.example.net;


import com.example.common.NetModel;
import com.example.common.SPUtility;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().addHeader("token", SPUtility.getString(NetModel.context)).build());
    }

}
