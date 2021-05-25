package com.shoppingmall.net;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Context context=TokenInterceptorContext.context;
        String string = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE).getString("token", "");
        Request request = chain.request();
        Request build = request.newBuilder().addHeader("token", string).build();
        Response proceed = chain.proceed(build);
        return proceed;
    }
}
