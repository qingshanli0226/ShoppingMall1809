package com.fiannce.bawei.net;



import com.fiannce.bawei.net.mode.CrashBean;
import com.fiannce.bawei.net.mode.FocusBean;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.VersionBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

//认为是IMode
public interface ShopmallApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    @GET("findFocusVideo")
    Observable<FocusBean> findFocus();

    @POST("crash")
    @FormUrlEncoded
    Observable<CrashBean> crashReport(@FieldMap HashMap<String,String> params);

}
