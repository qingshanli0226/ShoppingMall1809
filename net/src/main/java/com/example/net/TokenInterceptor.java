package com.example.net;


import java.io.IOException;

import okhttp3.Interceptor;

import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        return chain.proceed(null);
    }
}
