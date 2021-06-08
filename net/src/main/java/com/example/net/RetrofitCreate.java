package com.example.net;

import com.example.common.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreate {

    private static BusinessApi businessApiService;
    public static BusinessApi getFiannceApiService() {
        if (businessApiService ==null){
            businessApiService = createKSApiService();
        }
        return businessApiService;
    }

    private static BusinessApi createKSApiService() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new TokenInterceptor());
        okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okBuilder.writeTimeout(2, TimeUnit.MINUTES);
        okBuilder.readTimeout(2,TimeUnit.MINUTES);
        okBuilder.callTimeout(2,TimeUnit.MINUTES);
        OkHttpClient build = okBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(build)
                .baseUrl(Constants.BASE_URL).build();

        return retrofit.create(BusinessApi.class);
    }
}
