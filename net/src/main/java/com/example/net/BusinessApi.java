package com.example.net;


import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.CheckInventoryBean;
import com.example.common.bean.ClassifyBean;
import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.common.bean.FindForPayBean;
import com.example.common.bean.GoodsBean;
import com.example.common.bean.HomeBean;
import com.example.common.bean.KindAccessoryBean;
import com.example.common.bean.KindBagBean;
import com.example.common.bean.KindDigitBean;
import com.example.common.bean.KindDressBean;
import com.example.common.bean.KindGameBean;
import com.example.common.bean.KindHomeProductsBean;
import com.example.common.bean.KindJacketBean;
import com.example.common.bean.KindOvercoatBean;
import com.example.common.bean.KindPantsBean;
import com.example.common.bean.KindSkirtBean;
import com.example.common.bean.KindStationeryBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.RecommendBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.RemoveManyProductBean;
import com.example.common.bean.RemoveOneProductBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdateEmailBean;
import com.example.common.bean.UpdateMoneyBean;
import com.example.common.bean.UpdatePhoneBean;
import com.example.common.bean.UpdatePointBean;
import com.example.common.bean.UpdateProductNumBean;

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

public interface BusinessApi {
    //获取推荐产品信息的接口
    @GET("getRecommend")
    Observable<RecommendBean> getRecommendData();



    //主页面的数据
    @GET("/atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();

    @GET
    Observable<GoodsBean> getTypeData(@Url String url);

    //分类Fragment里面的标签Fragment页面数据
    @GET("/atguigu/json/TAG_URL.json")
    Observable<ClassifyBean> getTagData();

    //小裙子
    @GET("/atguigu/json/SKIRT_URL.json")
    Observable<KindSkirtBean> getSkirtData();

    //上衣
    @GET("/atguigu/json/JACKET_URL.json")
    Observable<KindJacketBean> getJacketData();

    //下装(裤子)
    @GET("/atguigu/json/PANTS_URL.json")
    Observable<KindPantsBean> getPantsData();

    //外套
    @GET("/atguigu/json/OVERCOAT_URL.json")
    Observable<KindOvercoatBean> getOvercoatData();

    //配件
    @GET("/atguigu/json/ACCESSORY_URL.json")
    Observable<KindAccessoryBean> getAccessoryData();

    //包包
    @GET("/atguigu/json/BAG_URL.json")
    Observable<KindBagBean> getBagData();

    //装扮
    @GET("/atguigu/json/DRESS_UP_URL.json")
    Observable<KindDressBean> getDressData();

    //居家宅品
    @GET("/atguigu/json/HOME_PRODUCTS_URL.json")
    Observable<KindHomeProductsBean> getHomeProductsData();

    //办公文具
    @GET("/atguigu/json/STATIONERY_URL.json")
    Observable<KindStationeryBean> getStationeryData();

    //数码周边
    @GET("/atguigu/json/DIGIT_URL.json")
    Observable<KindDigitBean> getDigitData();

    //游戏专区
    @GET("/atguigu/json/GAME_URL.json")
    Observable<KindGameBean> getGameData();


    @GET
    @Streaming
    Call<ResponseBody> DownFile(@Url String url);



    //注册
    @FormUrlEncoded
    @POST("register")
    Observable<RegBean> postRegister(@Field("name") String name, @Field("password") String password);
    //登录
    @FormUrlEncoded
    @POST("login")
    Observable<LogBean> postLogin(@Field("name") String name, @Field("password") String password);
    //自动登录
    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LogBean> postAutoLogin(@Field("token") String token);

    //更新用户绑定的电话
    @POST("updatePhone")
    Observable<UpdatePhoneBean> setUpdatePhone(@Body RequestBody requestBody);

    //更新现金的接口
    @POST("updateMoney")
    Observable<UpdateMoneyBean> setUpdateMoney(@Body RequestBody requestBody);

    //更新积分的接口
    @POST("updatePoint")
    Observable<UpdatePointBean> setUpdatePoint(@Body RequestBody requestBody);

    //更新邮箱的接口
    @POST("updateEmail")
    Observable<UpdateEmailBean> setUpdateEmail(@Body RequestBody requestBody);

    //更新地址的接口
    @POST("updateAddress")
    Observable<UpdateAddress> setUpdateAddress(@Body RequestBody requestBody);


    //请求添加产品的接口
    @POST("addOneProduct")
    Observable<AddOneProductBean> setAddOneProduct(@Body RequestBody requestBody);

    //获取服务端购物车产品信息的接口
    @GET("getShortcartProducts")
    Observable<ShortcartProductBean> getShortProductData();

    //更新服务端购物车产品的数量的接口
    @POST("updateProductNum")
    Observable<UpdateProductNumBean> setUpdateProductNum(@Body RequestBody requestBody);

    //检查服务端多个产品是否库存充足
    @POST("checkInventory")
    Observable<CheckInventoryBean> setCheckInventory(@Body RequestBody requestBody);

    //请求服务端，是否支付成功
    @POST("confirmServerPayResult")
    Observable<ConfirmServerPayResultBean> setConfirmServerPayResult(@Body RequestBody requestBody);

    //从服务端购物车删除一个产品的接口
    @POST("removeOneProduct")
    Observable<RemoveOneProductBean> setRemoveOneProduct(@Body RequestBody requestBody);

    //查找待支付的订单
    @GET("findForPay")
    Observable<FindForPayBean> getFindForPayData();

    //从服务端购物车删除一多产品的接口
    @POST("removeManyProduct")
    Observable<RemoveManyProductBean> setRemoveManyProduct(@Body RequestBody requestBody);

    //全选服务端购物车产品或者全不选
    @POST("selectAllProduct")
    Observable<SelectAllProductBean> SelectAllProduct(@Body RequestBody requestBody);


}
