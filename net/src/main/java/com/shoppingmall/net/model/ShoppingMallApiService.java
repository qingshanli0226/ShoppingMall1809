package com.shoppingmall.net.model;

import com.shoppingmall.net.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ShoppingMallApiService {
    //http://49.233.0.68:8080/atguigu/json/HOME_URL.json
    @GET("/atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();

}
