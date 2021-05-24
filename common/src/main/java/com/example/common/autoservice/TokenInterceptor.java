package com.example.common.autoservice;


import com.example.framework.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String string = SpUtil.getString(AppMoudel.context, FiannceContants.Token_key);
        Request build = chain.request().newBuilder().addHeader(FiannceContants.Token_key, string).build();
        return chain.proceed(build);
    }
}
