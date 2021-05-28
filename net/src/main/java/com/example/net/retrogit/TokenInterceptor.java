package com.example.net.retrogit;



import android.util.Log;


import com.example.net.AppMoudel;
import com.example.net.FiannceContants;
import com.example.net.SpUtil;

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
