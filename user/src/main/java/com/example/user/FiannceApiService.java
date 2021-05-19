package com.example.user;

import com.fiannce.net.mode.GestureBean;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.InvestBean;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.net.mode.VersionBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestBean> getProductData();

    @POST("register?")
    @FormUrlEncoded
    Observable<RegisterBean> getRegisterData(@Field("name") String name, @Field("password") String password);

    @POST("login?")
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST(AppNetConfig.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token") String token);

    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);

    @POST("setGesturePassword")
    Observable<GestureBean> setPassword(@Body RequestBody requestBody);

    @POST("loginByGesturePassword")
    Observable<GestureBean> loginPassword(@Body RequestBody requestBody);

    @POST("clearGesturePassword")
    Observable<GestureBean> clearPassword(@Body RequestBody requestBody);
}
