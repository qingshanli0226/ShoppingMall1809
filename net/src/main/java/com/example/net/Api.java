package com.example.net;

import com.example.net.bean.AccessoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.HomeProductBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Overconat;
import com.example.net.bean.PantsBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @GET(Constants.HOME_URL)
    Observable<HomeBean> getHomebean();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("name") String name, @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("name") String name, @Field("password") String password);

    @GET(Constants.SKIRT_URL)
    Observable<SkirtBean> getSkirt();
    @GET(Constants.JACKET_URL)
    Observable<JacketBean> getJack();
    @GET(Constants.PANTS_URL)
    Observable<PantsBean> getPants();
    @GET(Constants.OVERCOAT_URL)
    Observable<Overconat> getOver();
    @GET(Constants.ACCESSORY_URL)
    Observable<AccessoryBean> getAccess();
    @GET(Constants.BAG_URL)
    Observable<BagBean> getBag();
    @GET(Constants.DRESS_UP_URL)
    Observable<DressBean> getDress();
@GET(Constants.HOME_PRODUCTS_URL)
    Observable<HomeProductBean> getHomee();
@GET(Constants.STATIONERY_URL)
    Observable<StationeryBean> getStati();
@GET(Constants.DIGIT_URL)
    Observable<DigitBean> getDigit();
    @GET(Constants.GAME_URL)
    Observable<GameBean> getGame();

}
