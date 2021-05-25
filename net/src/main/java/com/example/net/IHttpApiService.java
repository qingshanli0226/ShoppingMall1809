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
    Observable<SelectBean> inventory(@Field("name")String name, @Field("password")String password);


    //选中一个
    @POST(Constants.UPDATEPRODUCTSELECTED)
    Observable<SelectBean> updateProductSelect(@Body RequestBody requestBody);

    //更新服务端购物车产品的数量的接口
    @POST(Constants.UPDATEPRODUCTNUM)
    Observable<UpdateProductNumBean> updateProductNum(@Body RequestBody requestBody);


    //选中全部
    @POST(Constants.SELECTALL)
    Observable<SelectBean> selectAll(@Body RequestBody requestBody);


}
