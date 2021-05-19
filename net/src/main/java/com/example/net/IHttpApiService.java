package com.example.net;


import com.example.common.Constants;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;


import com.example.net.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IHttpApiService {

//    @GET(AppNetConfig.UPDATE)
//    Observable<UpdateBean> getAppUpdate();
//
//    @GET(AppNetConfig.PRODUCT)
//    Observable<ProductBean> getProduct();
//
//
    @FormUrlEncoded
    @POST(Constants.USERREGISTER)
    Observable<RegisterBean> getRegister(@Field("name")String name, @Field("password")String password);


    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Observable<LoginBean> getLogin(@Field("name")String name, @Field("password")String password);

    @FormUrlEncoded
    @POST(Constants.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token")String token);

//
//
//    @POST(AppNetConfig.LOGOUT)
//    Observable<GesturePassword> exit();
//    //下载
//    @Streaming
//    @GET
//    Observable<ResponseBody> getDownLoad(@Url String url);
//
//
//    //设置密码
//    @POST(AppNetConfig.SETGESTUREPASSWORD)
//    Observable<GesturePassword> setGesturePassword(@Body RequestBody requestBody);
//    //登录密码
//    @POST(AppNetConfig.LOGINGESTUREPASSWORD)
//    Observable<GesturePassword> loginGesturePassword(@Body RequestBody requestBody);
//    //清除密码
//    @POST(AppNetConfig.CLEARESTUREPASSWORD)
//    Observable<GesturePassword> clearPassword();


    @GET(Constants.HOME_URL)
    Observable<HomeBean> getHomeData();
}
