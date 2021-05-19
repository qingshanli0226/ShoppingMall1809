package com.fiannce.bawei.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.common.SpUtil;
import com.fiannce.bawei.net.NetModule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//增加一个token拦截器，可以确保每次网络请求都会在请求头添加一个token,可以避免在每个网络请求时添加一次token
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String token= SpUtil.getString(NetModule.context,FiannceConstants.TOKEN_KEY);
        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader(FiannceConstants.TOKEN_KEY, token).build();

        return chain.proceed(newRequest);
    }
}
