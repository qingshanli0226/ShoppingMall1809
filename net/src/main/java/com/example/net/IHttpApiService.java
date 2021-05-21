package com.example.net;


import com.example.common.Constants;
import com.example.net.bean.LabelBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;


import com.example.net.bean.HomeBean;
import com.example.net.bean.TypeBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

    @GET(Constants.TAG_URL)
    Observable<LabelBean> getLabel();


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



    @GET(Constants.SKIRT_URL)
    Observable<TypeBean> getSkirt();


    @GET(Constants.JACKET_URL)
    Observable<TypeBean> getJacket();


    @GET(Constants.PANTS_URL)
    Observable<TypeBean> getPants();


    @GET(Constants.OVERCOAT_URL)
    Observable<TypeBean> getOvercoat();


    @GET(Constants.ACCESSORY_URL)
    Observable<TypeBean> getAccessory();


    @GET(Constants.BAG_URL)
    Observable<TypeBean> getBag();


    @GET(Constants.DRESS_UP_URL)
    Observable<TypeBean> getDress();


    @GET(Constants.HOME_PRODUCTS_URL)
    Observable<TypeBean> getProducts();


    @GET(Constants.STATIONERY_URL)
    Observable<TypeBean> getStationery();


    @GET(Constants.DIGIT_URL)
    Observable<TypeBean> getDigit();


    @GET(Constants.GAME_URL)
    Observable<TypeBean> getGame();

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);



}
