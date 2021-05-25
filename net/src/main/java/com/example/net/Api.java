package com.example.net;

import com.example.net.bean.FindForPayBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;
import com.example.net.bean.SkirtBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
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

    //待支付
    @GET("findForPay")
    Observable<FindForPayBean> getForPay();

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    //购物车
    @POST("addOneProduct")
    Observable<ShoppingCartBean> getAddShoppingCart(@Body RequestBody body);

    //库存
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<RegisterBean> getInventory(@Field("productId") String productId,@Field("productNum") String productNum);

    //自动登录
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> getAutoLogin(@Field("token") String token);
}
