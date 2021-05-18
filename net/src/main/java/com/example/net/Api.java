package com.example.net;

import com.example.net.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
public interface Api {
@GET(Constants.BASE_URL)
    Observable<HomeBean> getHomebean();

}
