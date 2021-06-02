package com.shoppingmall.net.model;

import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.CheckProductBean;
import com.shoppingmall.net.bean.ConfirmServerPayResultBean;
import com.shoppingmall.net.bean.FindForSendBean;
import com.shoppingmall.net.bean.OrderBean;
import com.shoppingmall.net.bean.FindForPayBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.BuyBean;
import com.shoppingmall.net.bean.GoodsBean;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.LabelBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.RegisterBean;
import com.shoppingmall.net.bean.SelectBean;
import com.shoppingmall.net.bean.ShopCarBean;

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

    //添加购物车
    @POST("addOneProduct")
    Observable<SelectBean> addProduct(@Body RequestBody body);

    //获取购物车数据
    //http://49.233.0.68:8080/getShortcartProducts
    @GET("getShortcartProducts")
    Observable<ShopCarBean> getShopCarProduct();

    //检查服务端一个产品库存情况的接口
    //http://49.233.0.68:8080/checkOneProductInventory
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<SelectBean> checkProduct
    (@Field("productId")int productId,@Field("productNum")int productNum);

    //更新服务端购物车产品的数量的接口
    //http://49.233.0.68:8080/updateProductNum
    @POST("updateProductNum")
    Observable<SelectBean> updateProduct(@Body RequestBody body);

    //选中一个\
    @POST("updateProductSelected")
    Observable<SelectBean> updateProductSelect(@Body RequestBody requestBody);

    //选中全部
    @POST("selectAllProduct")
    Observable<SelectBean> selectAll(@Body RequestBody requestBody);

    //删除
    @POST("removeOneProduct")
    Observable<SelectBean> removeOneProduct(@Body RequestBody requestBody);

    //删除全部
    @POST("removeManyProduct")
    Observable<SelectBean> removeMany(@Body RequestBody requestBody);

    //向服务端下订单接口
    @POST("getOrderInfo")
    Observable<OrderBean> orderInfo(@Body RequestBody requestBody);

    //设置电话
    @FormUrlEncoded
    @POST("updatePhone")
    Observable<SelectBean> setPhone(@Field("phone") String phone);

    //设置地址
    @FormUrlEncoded
    @POST("updateAddress")
    Observable<SelectBean> setAddress(@Field("address") String address);

    //请求服务端，是否支付成功
    @POST("confirmServerPayResult")
    Observable<SelectBean> confirmServerPayResult(@Body RequestBody body);

    //待支付
    @GET("findForPay")
    Observable<FindForPayBean> findForPay();

    //代发货
    @GET("findForSend")
    Observable<FindForSendBean> findForSend();

}
