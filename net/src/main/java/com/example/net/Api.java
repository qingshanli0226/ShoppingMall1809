package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
@GET(Constants.BASE_URL)
    Observable<HomeBean> getHomebean();
@POST("register")
@FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("name")String name,@Field("password")String password);
}
