package com.example.net;

import com.example.commom.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    private static ShopApiService shopApiService;

    public static ShopApiService getShopApiService() {
        if (shopApiService == null) {
            shopApiService =createKSApiService();
        }
        return shopApiService;
    }

    private static ShopApiService createKSApiService() {

        ShopApiService shopApiService = new Retrofit.Builder()
                .client(
                        new OkHttpClient.Builder()
                                .writeTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                                .addInterceptor(new TokenInterceptor())
                                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                .build()
                )
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ShopApiService.class);

        return shopApiService;

    }


}
