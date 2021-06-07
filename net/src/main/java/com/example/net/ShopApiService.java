package com.example.net;

import com.example.commom.Constants;
import com.example.net.model.CategoryBean;
import com.example.net.model.FindForBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.net.model.SortBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
    //主页面
    @GET(Constants.HOME_URL)
    Observable<HoemBean> getHomeData();
    //注册
    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegisterData(@Field("name") String username, @Field("password") String password);
    //退出登录
    @POST("logout")
    Observable<RegisterBean> getLogout();
    //登录
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLoginData(@Field("name") String username, @Field("password") String password);
    //自动登录
    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoLoginData(@Field("token") String token);
    //购物车请求
    @GET("getShortcartProducts")
    Observable<ShoppingTrolleyBean> getShoppingTrolley();

    //分类
    @GET
    Observable<CategoryBean> getTypeData(@Url String url);

    //标签
    @GET(Constants.TAG_URL)
    Observable<SortBean> getSortData();
    //添加购物车
    @POST("addOneProduct")
    Observable<RegisterBean> addOneProduct(@Body RequestBody requestBody);
    //库存
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<RegisterBean> getCheckOneProductInventory(@Field("productId")String productId,@Field("productNum")String productNum);
    //修改选择
    @POST("updateProductSelected")
    Observable<RegisterBean> getUpdateProductSelected(@Body RequestBody requestBody);
    //全选
    @POST("selectAllProduct")
    Observable<RegisterBean> getSelectAllProduct(@Body RequestBody requestBody);
    //删除
    @POST("removeManyProduct")
    Observable<RegisterBean> getRemoveManyProduct(@Body RequestBody requestBody);
    //单选
    @POST("checkInventory")
    Observable<ShoppingTrolleyBean> getCheckInventory(@Body RequestBody requestBody);
    //修改数量
    @POST("updateProductNum")
    Observable<RegisterBean> getUpDateProductNum(@Body RequestBody requestBody);

    //向服务端下订单接口
    @POST("getOrderInfo")
    Observable<OrderinfoBean> getOrderInfo(@Body RequestBody requestBody);

    //向服务端下订单接口
    @POST("confirmServerPayResult")
    Observable<RegisterBean> getConfirmServerPayResult(@Body RequestBody requestBody);

    //待支付
    @GET("findForPay")
    Observable<FindForBean> getFindForPayData();

    //待发货
    @GET("findForSend")
    Observable<FindForBean> getFindForSendData();

    //上传错误
    @GET("crash")
    Observable<FindForBean> getCrash(@FieldMap HashMap<String,String> params);

    //更新用户绑定的电话
    @POST("updatePhone")
    @FormUrlEncoded
    Observable<RegisterBean> getUpDataPhone(@Field("phone")String phone);

    //更新地址的接口
    @POST("updateAddress")
    @FormUrlEncoded
    Observable<RegisterBean> getUpDataAddress(@Field("address")String address);
}
