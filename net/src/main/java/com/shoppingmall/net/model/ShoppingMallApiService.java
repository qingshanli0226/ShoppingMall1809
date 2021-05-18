package com.shoppingmall.net.model;

import com.shoppingmall.net.bean.AccessoryBean;
import com.shoppingmall.net.bean.BagBean;
import com.shoppingmall.net.bean.BuyBean;
import com.shoppingmall.net.bean.DialogBean;
import com.shoppingmall.net.bean.DigitBean;
import com.shoppingmall.net.bean.DressBean;
import com.shoppingmall.net.bean.GameBean;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.Home_ProductsBean;
import com.shoppingmall.net.bean.JacketBean;
import com.shoppingmall.net.bean.LabelBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.OvercoatBean;
import com.shoppingmall.net.bean.PantsBean;
import com.shoppingmall.net.bean.RegisterBean;
import com.shoppingmall.net.bean.SkirtBean;
import com.shoppingmall.net.bean.StationeryBean;

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
    //下衣
    @GET("/atguigu/json/PANTS_URL.json")
    Observable<PantsBean> getPantsData();
    //外套
    @GET("/atguigu/json/OVERCOAT_URL.json")
    Observable<OvercoatBean> getOvercoatData();
    //配件
    @GET("/atguigu/json/ACCESSORY_URL.json")
    Observable<AccessoryBean> getAccessoryData();
    //包包
    @GET("/atguigu/json/BAG_URL.json")
    Observable<BagBean> getBagData();

    //装扮
    @GET("/atguigu/json/DRESS_UP_URL.json")
    Observable<DressBean> getDressData();

    //居家宅品
    @GET("/atguigu/json/HOME_PRODUCTS_URL.json")
    Observable<Home_ProductsBean> getHomeProductsData();


    //办公文具
    @GET("/atguigu/json/STATIONERY_URL.json")
    Observable<StationeryBean> getStationeryData();

    //数码周边
    @GET("/atguigu/json/DIGIT_URL.json")
    Observable<DigitBean> getDigitData();

    //游戏专区
    @GET("/atguigu/json/GAME_URL.json")
    Observable<GameBean> getGameData();





}
