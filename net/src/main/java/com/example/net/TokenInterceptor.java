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

    //424cd6a0-65f5-4bc2-b519-8ff3d8ff7464AND1621598949535

    //452e2dd3-6381-4760-bff8-1b4768ae044bAND1621599050514
}
