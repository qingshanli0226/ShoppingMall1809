package com.example.net;

import com.example.commom.Constants;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;

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

public interface ShopApiService {

    @GET(Constants.HOME_URL)
    Observable<HoemBean> getHomeData();

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegisterData(@Field("name") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLoginData(@Field("name") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoLoginData(@Field("token") String token);


}
