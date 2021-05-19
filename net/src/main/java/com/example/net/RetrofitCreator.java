package com.example.net;


import com.example.net.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//定义网络框架
public class RetrofitCreator {

    private static FiannceApiService fiannceApiService;


    public static FiannceApiService getFiannceApiService() {
        if (fiannceApiService == null) {
            fiannceApiService = createKSApiService();
        }

        return fiannceApiService;
    }

    private static FiannceApiService createKSApiService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new TokenInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(FiannceApiService.class);
    }
}
