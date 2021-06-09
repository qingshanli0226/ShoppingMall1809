package com.example.net;


import com.example.common.Constants;
import com.example.net.bean.AwaitPaymentBean;
import com.example.net.bean.CartBean;
import com.example.net.bean.CheckNumAll;
import com.example.net.bean.LabelBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderBean;
import com.example.net.bean.RegisterBean;


import com.example.net.bean.HomeBean;
import com.example.net.bean.SelectBean;
import com.example.net.bean.ShipmentBean;
import com.example.net.bean.TypeBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface IHttpApiService {

    @FormUrlEncoded
    @POST(Constants.USERREGISTER)
    Observable<RegisterBean> getRegister(@Field("name")String name, @Field("password")String password);
    //安全
    @FormUrlEncoded
    @POST(Constants.USERREGISTER)
    Observable<RegisterBean> getRegister(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Observable<LoginBean> getLogin(@Field("name")String name, @Field("password")String password);

    //安全
    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Observable<LoginBean> getLogin(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST(Constants.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token")String token);
    @FormUrlEncoded
    @POST(Constants.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@FieldMap Map<String,String> map);
    @GET(Constants.TAG_URL)
    Observable<LabelBean> getLabel();
    //代付款
    @GET(Constants.AWAITPAYMENT)
    Observable<AwaitPaymentBean> getAwaitPayment();
    //代发货
    @GET(Constants.SHIPMENT)
    Observable<ShipmentBean> getShipment();
    //首页
    @GET(Constants.HOME_URL)
    Observable<HomeBean> getHomeData();

    //分类
    @GET
    Observable<TypeBean> getType(@Url String url);

    //glide下载
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    //添加数据
    @POST(Constants.ADDPRODUCT)
    Observable<SelectBean> addProduct(@Body RequestBody requestBody);

    //获取服务端购物车产品信息的接口
    @GET(Constants.SHORTACRT)
    Observable<CartBean> showCart();


    //    检查服务端一个产品库存情况的接口
    @FormUrlEncoded
    @POST(Constants.CHECKONEPRODUCTINVENTORY)
    Observable<SelectBean> inventory(@Field("productId") int productId,@Field("productNum") int productNum);

    @FormUrlEncoded
    @POST(Constants.CHECKONEPRODUCTINVENTORY)
    Observable<SelectBean> inventory(@FieldMap Map<String,String> map);
    //    检查服务端一个产品库存情况的接口
    @POST(Constants.CHECKINVENTORY)
    Observable<CheckNumAll> inventoryAll(@Body RequestBody requestBody);
    //选中一个
    @POST(Constants.UPDATEPRODUCTSELECTED)
    Observable<SelectBean> updateProductSelect(@Body RequestBody requestBody);

    //更新服务端购物车产品的数量的接口
    @POST(Constants.UPDATEPRODUCTNUM)
    Observable<SelectBean> updateProductNum(@Body RequestBody requestBody);


    //选中全部
    @POST(Constants.SELECTALL)
    Observable<SelectBean> selectAll(@Body RequestBody requestBody);

    //删除
    @POST(Constants.REMOVEPRODUCT)
    Observable<SelectBean> removeOneProduct(@Body RequestBody requestBody);

    //删除全部
    @POST(Constants.REMOVEMANY)
    Observable<SelectBean> removeMany(@Body RequestBody requestBody);


    //订单接口
    @POST(Constants.GETORDER)
    Observable<OrderBean> getOrder(@Body RequestBody requestBody);
    //设置电话
    @FormUrlEncoded
    @POST(Constants.UPDATEPHONE)
    Observable<SelectBean> setPhone(@Field("phone") String phone);

    //设置地址
    @FormUrlEncoded
    @POST(Constants.UPDATEADDRESS)
    Observable<SelectBean> setAddr(@Field("address") String address);

    //判断是否支付成功
    @POST(Constants.CONFIRMSERVER)
    Observable<SelectBean> configPayCheck(@Body RequestBody requestBody);
}
