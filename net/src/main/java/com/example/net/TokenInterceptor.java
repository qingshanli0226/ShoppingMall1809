package com.example.net;

import com.example.commened.FiannceContants;
import com.example.commened.SpUtil;
import com.example.net.module.NetModule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
            String token = SpUtil.getString(NetModule.context, FiannceContants.TOKEN_KEY);
        Request newRequest = request.newBuilder().addHeader(FiannceContants.TOKEN_KEY, token).build();
        return chain.proceed(newRequest);
    }

}
