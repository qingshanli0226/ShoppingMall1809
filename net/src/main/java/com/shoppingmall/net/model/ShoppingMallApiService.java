package com.shoppingmall.net.model;

import com.shoppingmall.net.bean.AccessoryBean;
import com.shoppingmall.net.bean.BagBean;
import com.shoppingmall.net.bean.BuyBean;
import com.shoppingmall.net.bean.DialogBean;
import com.shoppingmall.net.bean.DigitBean;
import com.shoppingmall.net.bean.DressBean;
import com.shoppingmall.net.bean.GameBean;
import com.shoppingmall.net.bean.GoodsBean;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ShoppingMallApiService {
    //http://49.233.0.68:8080/atguigu/json/HOME_URL.json
    //主页
    @GET("atguigu/json/HOME_URL.json")
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
    @GET
    Observable<GoodsBean> Goods(@Url String url);
    //Glide
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);
    //所有商品

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAuto(@Field("token")String token);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoLogin(@Field("token")String token);
}
