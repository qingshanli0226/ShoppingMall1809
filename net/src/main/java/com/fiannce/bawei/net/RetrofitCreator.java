package com.fiannce.bawei.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

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
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://49.233.0.68:8080/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(FiannceApiService.class);
    }
}
