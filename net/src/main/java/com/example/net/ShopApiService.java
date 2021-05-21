package com.example.net;

import com.example.commom.Constants;
import com.example.net.model.CategoryBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.net.model.SortBean;

import io.reactivex.Observable;
import retrofit2.Call;
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

    @GET("getShortcartProducts")
    Observable<ShoppingTrolleyBean> getShoppingTrolley();
    //小裙子
    @GET(Constants.SKIRT_URL)
    Observable<CategoryBean> getSkirtData();

    //上衣
    @GET(Constants.JACKET_URL)
    Observable<CategoryBean> getJacketData();
//下装(裤子)

    @GET(Constants.PANTS_URL)
    Observable<CategoryBean> getPantsData();
//外套

    @GET(Constants.OVERCOAT_URL)
    Observable<CategoryBean> getOvercoatData();

    //配件
    @GET(Constants.ACCESSORY_URL)
    Observable<CategoryBean> getAccessoryData();

    //包包
    @GET(Constants.BAG_URL)
    Observable<CategoryBean> getBugData();

    //装扮
    @GET(Constants.DRESS_UP_URL)
    Observable<CategoryBean> getDressData();

    //居家宅品
    @GET(Constants.HOME_PRODUCTS_URL)
    Observable<CategoryBean> getProductsData();

    //办公文具
    @GET(Constants.STATIONERY_URL)
    Observable<CategoryBean> getStationeryData();

    //数码周边
    @GET(Constants.DIGIT_URL)
    Observable<CategoryBean> getDigitData();

    //游戏专区
    @GET(Constants.GAME_URL)
    Observable<CategoryBean> getGameData();

    //标签
    @GET(Constants.TAG_URL)
    Observable<SortBean> getSortData();

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Body String url);
}
