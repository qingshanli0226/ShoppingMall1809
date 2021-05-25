package com.example.net;



import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SpUtil.getString(AppMoudel.context, FiannceContants.TOKEN_KEY);
        Request build = chain.request().newBuilder().addHeader(FiannceContants.TOKEN_KEY, token).build();
        return chain.proceed(build);
    }
}
