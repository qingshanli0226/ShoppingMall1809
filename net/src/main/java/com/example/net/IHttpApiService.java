package com.example.net;


import com.example.common.Constants;
import com.example.net.bean.CartBean;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.LabelBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.RegisterBean;


import com.example.net.bean.HomeBean;
import com.example.net.bean.SelectBean;
import com.example.net.bean.TypeBean;
import com.example.net.bean.UpdateProductNumBean;

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

    @GET
    Observable<TypeBean> getType(@Url String url);

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);


    @POST(Constants.ADDPRODUCT)
    Observable<ProductBean> addProduct(@Body RequestBody requestBody);


    @GET(Constants.SHORTACRT)
    Observable<CartBean> showCart();



    @POST(Constants.CHECKONEPRODUCTINVENTORY)
    Observable<ProductBean> inventory(@Body ResponseBody responseBody);


    @POST(Constants.UPDATEPRODUCTSELECTED)
    Observable<SelectBean> updateProductSelect(@Body RequestBody requestBody);

    @POST(Constants.UPDATEPRODUCTNUM)
    Observable<UpdateProductNumBean> UpdateProductNum();


}
