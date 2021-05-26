package com.shoppingmall.net.model;

import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.CheckProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.BuyBean;
import com.shoppingmall.net.bean.GoodsBean;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.LabelBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.RegisterBean;
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
    Observable<AddProductBean> addProduct(@Body RequestBody body);

    //获取购物车数据
    //http://49.233.0.68:8080/getShortcartProducts
    @GET("getShortcartProducts")
    Observable<ShopCarBean> getShopCarProduct();

    //检查服务端一个产品库存情况的接口
    //http://49.233.0.68:8080/checkOneProductInventory
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<CheckProductBean> checkProduct
    (@Field("productId")String productId,@Field("productNum")String productNum);

    //更新服务端购物车产品的数量的接口
    //http://49.233.0.68:8080/updateProductNum
    @POST("updateProductNum")
    Observable<ProductBean> updateProduct(@Body RequestBody body);
}
