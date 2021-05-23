package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.SkirtBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {
    @GET(Constants.HOME_URL)
    Observable<HomeBean> getHomebean();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("name") String name, @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("name") String name, @Field("password") String password);

    @GET
    Observable<SkirtBean> getSkirt(@Url String url);

}
