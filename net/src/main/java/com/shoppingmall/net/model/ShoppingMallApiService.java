package com.shoppingmall.net.model;

import com.shoppingmall.net.bean.BuyBean;
import com.shoppingmall.net.bean.DialogBean;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.JacketBean;
import com.shoppingmall.net.bean.LabelBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.RegisterBean;
import com.shoppingmall.net.bean.SkirtBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ShoppingMallApiService {
    //http://49.233.0.68:8080/atguigu/json/HOME_URL.json
    @GET("/atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();
    //http://49.233.0.68:8080/atguigu/json/TAG_URL.json
    //分类标签
    @GET("/atguigu/json/TAG_URL.json")
    Observable<LabelBean> getLabelData();
    //注册
    @FormUrlEncoded
    @POST("register?")
    Observable<RegisterBean> getRegisterData(@Field("name") String name, @Field("password") String password);
    //登录
    @FormUrlEncoded
    @POST("login?")
    Observable<LoginBean> getLoginData(@Field("name") String name, @Field("password") String password);
    //http://49.233.0.68:8080/atguigu/json/NEW_POST_URL.json
    //抢购图片
    @GET("/atguigu/json/NEW_POST_URL.json")
    Observable<BuyBean> getBuyData();

    //http://49.233.0.68:8080/atguigu/json/HOT_POST_URL.json
    //对话图片
    @GET("/atguigu/json/HOT_POST_URL.json")
    Observable<DialogBean> getDialogData();
    //小裙子
    @GET("/atguigu/json/SKIRT_URL.json")
    Observable<SkirtBean> getSkirtData();
    //上衣
    @GET("/atguigu/json/JACKET_URL.json")
    Observable<JacketBean> getJacketData();

}
