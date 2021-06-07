package com.example.net;



import com.example.net.bean.CrashBean;
import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.CheckInventoryBean;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.RemoveManyProductBean;
import com.example.net.bean.business.RemoveOneProductBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.business.UpdateProductSelectedBean;

import com.example.net.bean.classify.ClassBean;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;
import com.example.net.bean.user.LoginBean;
import com.example.net.bean.user.RegisterBean;

import com.example.net.bean.HomeBean;

import com.example.net.bean.TabBean;

import com.example.net.bean.user.UpdateAddressBean;
import com.example.net.bean.user.UpdatePhoneBean;
import com.example.net.constants.Constants;

import java.util.HashMap;

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
import retrofit2.http.Streaming;
import retrofit2.http.Url;


//认为是IMode
public interface FiannceApiService {

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    //主页面HoemFrgment数据
    @GET(Constants.HOME_URL)
    Observable<HomeBean> getHoemData();

    //分类Fragment里面的标签Fragment页面数据
    @GET(Constants.TAG_URL)
    Observable<TabBean> getTabData();
    /**
     * 分类页面
     * Class
     */
    @GET()
    Observable<ClassBean> getClassData(@Url String url);

    /**
     * 用户
     * User
     */

    //注册
    @POST(Constants.USER_REGISTER)
    @FormUrlEncoded
    Observable<RegisterBean> getRegisterData(@Field("name") String name, @Field("password") String password);

    //登录
    @POST(Constants.USER_LOGIN)
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@Field("name") String name, @Field("password") String password);

    //自动登录
    @FormUrlEncoded
    @POST(Constants.USER_AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token") String token);

    //更新用户绑定的电话
    @POST(Constants.USER_UPDATEPHONE)
    @FormUrlEncoded
    Observable<UpdatePhoneBean> getUpDataPhone(@Field("phone")String phone);

    //更新地址的接口
    @POST(Constants.USER_UPDATEADDRESS)
    @FormUrlEncoded
    Observable<UpdateAddressBean> getUpdateAddress(@Field("address")String address);

    /**
     * 业务
     * Business
     * @param body
     */
    //向服务端购物车添加一个产品的接口
    @POST(Constants.BUSINESS_ADDONEPRODUCT)
    Observable<AddOneProductBean> getAddOneProduct(@Body RequestBody body);

    //从服务端购物车删除多个产品的接口
    @POST(Constants.BUSINESS_REMOVEMANYPRODUCT)
    Observable<RemoveManyProductBean> removeManyProduct(@Body RequestBody body);

   //从服务端购物车删除多个产品的接口
    @POST(Constants.BUSINESS_REMOVEONEPRODUCT)
    Observable<RemoveOneProductBean> removeOneProductBean(@Body RequestBody body);

    //更新服务端购物车产品的数量的接口
    @POST(Constants.BUSINESS_UPDATEPRODUCTNUM)
    Observable<UpdateProductNumBean> getUpdateProductNum(@Body RequestBody body);

    //更新服务端购物车产品的选择
    @POST(Constants.BUSINESS_UPDATEPRODUCTSELECTED)
    Observable<UpdateProductSelectedBean> getUpdateProductSelected(@Body RequestBody body);

    //全选服务端购物车产品或者全不选
    @POST(Constants.BUSINESS_SELECTALLPRODUCT)
    Observable<SelectAllProductBean> getSelectAllProduct(@Body RequestBody body);

    //检查服务端多个产品是否库存充足
    @POST(Constants.BUSINESS_CHECKINVENTORY)
    Observable<CheckInventoryBean> getCheckInventory(@Body RequestBody body);

    //向服务端下订单接口
    @POST(Constants.BUSINESS_GETORDERINFO)
    Observable<GetOrderInfoBean> getOrderInfo(@Body RequestBody body);

    //请求服务端，是否支付成功
    @POST(Constants.BUSINESS_CONFIRMSERVERPAYRESULT)
    Observable<ConfirmServerPayResultBean> getConfirmServerPayResult(@Body RequestBody body);

    //查找待支付的订单
    @GET(Constants.BUSINESS_FINDFORSEND)
    Observable<FindForSendbean> getFindforsend();


    //查找带发货订单
    @GET(Constants.BUSINESS_FINDFORPAY)
    Observable<FindForPayBean> getFindforpay();

    //获取服务端购物车产品信息的接口
    @GET(Constants.BUSINESS_GETSHORCARTPRODUCTS)
    Observable<GetShortcartProductsBean> getShortcartProductsBean();


    @FormUrlEncoded
    @POST(Constants.BUSINESS_CHECKONEPRODUCTINVENTORY)
    Observable<CheckOneInventoryBean> getCheckOneInventory(@Field("productId")String productId, @Field("productNum")String productNum);



    @POST("crash")
    @FormUrlEncoded
    Observable<CrashBean> crashReport(@FieldMap HashMap<String,String> params);




}
