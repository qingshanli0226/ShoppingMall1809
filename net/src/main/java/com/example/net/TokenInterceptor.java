package com.example.net;

import com.example.commom.ShopConstants;
import com.example.commom.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String string = SpUtil.getString(NetModule.context, ShopConstants.TOKEN_KEY);
        return chain.proceed(chain.request().newBuilder().addHeader(ShopConstants.TOKEN_KEY,string).build());
    }
}
