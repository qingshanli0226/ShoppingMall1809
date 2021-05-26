package com.example.net;

import com.example.commom.Constants;
import com.example.net.model.CategoryBean;
import com.example.net.model.FindForBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.net.model.SortBean;

import io.reactivex.Observable;
import retrofit2.Call;
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

public interface ShopApiService {

    //Glid 下载
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

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

    //分类
    @GET
    Observable<CategoryBean> getTypeData(@Url String url);

    //标签
    @GET(Constants.TAG_URL)
    Observable<SortBean> getSortData();

    @POST("addOneProduct")
    Observable<RegisterBean> addOneProduct(@Body RequestBody requestBody);

    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<RegisterBean> getCheckOneProductInventory(@Field("productId")String productId,@Field("productNum")String productNum);

    @POST("updateProductSelected")
    Observable<RegisterBean> getUpdateProductSelected(@Body RequestBody requestBody);

    @POST("selectAllProduct")
    Observable<RegisterBean> getSelectAllProduct(@Body RequestBody requestBody);

    @POST("removeManyProduct")
    Observable<RegisterBean> getRemoveManyProduct(@Body RequestBody requestBody);

    @POST("checkInventory")
    Observable<ShoppingTrolleyBean> getCheckInventory(@Body RequestBody requestBody);

    @POST("updateProductNum")
    Observable<RegisterBean> getUpDateProductNum(@Body RequestBody requestBody);

    //待支付
    @GET("findForPay")
    Observable<FindForBean> getFindForPayData();

    //待发货
    @GET("findForSend")
    Observable<FindForBean> getFindForSendData();


}
