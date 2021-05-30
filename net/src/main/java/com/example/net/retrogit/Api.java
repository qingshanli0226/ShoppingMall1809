package com.example.net.retrogit;

import com.example.net.Constants;
import com.example.net.bean.CheckInventoryBean;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderinfoBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.AddShoppingCartBean;
import com.example.net.bean.ShoppingCartBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.UpdateAddress;
import com.example.net.bean.UpdatePhone;

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

    //购物车
    @GET("getShortcartProducts")
    Observable<ShoppingCartBean> getShoppingCart();

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    //购物车
    @POST("addOneProduct")
    Observable<AddShoppingCartBean> getAddShoppingCart(@Body RequestBody body);

    //库存
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<RegisterBean> getInventory(@Field("productId") String productId, @Field("productNum") String productNum);

    //更新服务端购物车产品的数量的接口
    @POST("updateProductNum")
    Observable<RegisterBean> updataShoppingNum(@Body RequestBody body);

    //从服务端购物车删除一个产品的接口
    @POST("removeOneProduct")
    Observable<RegisterBean> deleteOneShopping(@Body RequestBody body);

    //更新服务端购物车产品的选择
    @POST("updateProductSelected")
    Observable<RegisterBean> getUpdateProductSelected(@Body RequestBody body);

    //全选服务端购物车产品或者全不选
    @POST("selectAllProduct")
    Observable<RegisterBean> updateAllSelected(@Body RequestBody body);

    //检查服务端多个产品是否库存充足
    @POST("checkInventory")
    @FormUrlEncoded
    Observable<CheckInventoryBean> getCheckInventory(@Body RequestBody body);

    //从服务端购物车删除多个产品的接口
    @POST("removeManyProduct")
    Observable<RegisterBean> getRemoveManyProduct(@Body RequestBody body);

    //自动登录
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> getAutoLogin(@Field("token") String token);

    //待发货
    @GET("findForSend")
    Observable<FindForSendBean> getForSend();

    //改手机
    @POST("updatePhone")
    @FormUrlEncoded
    Observable<UpdatePhone> getUpdatephone(@Field("phone")String phone);

    @POST("updateAddress")
    @FormUrlEncoded
    Observable<UpdateAddress> getupdataAddress(@Field("address")String address);

    //向服务端下订单
    @POST("getOrderInfo")
    Observable<OrderinfoBean> getOrderInfo(@Body RequestBody body);

}
