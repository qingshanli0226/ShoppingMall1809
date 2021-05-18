package com.example.net;


import com.example.common.bean.LogBean;
import com.example.common.bean.RecommendBean;
import com.example.common.bean.RegBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BusinessApiService {
    //获取推荐产品信息的接口
    @GET("getRecommend")
    Observable<RecommendBean> getHomeData();

    //注册
    @FormUrlEncoded
    @POST("register")
    Observable<RegBean> postRegister(@Field("name") String name, @Field("password") String password);
    //登录
    @FormUrlEncoded
    @POST("login")
    Observable<LogBean> postLogin(@Field("name") String name, @Field("password") String password);
    //自动登录
    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LogBean> postAutoLogin(@Field("token") String token);



}
