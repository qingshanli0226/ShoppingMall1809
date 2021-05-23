package com.example.framework.manager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.MainThread;

import com.example.net.BuildConfig;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheShopManager {
    private static CacheShopManager cacheShopManager;

    private CacheShopManager() {

    }

    public synchronized static CacheShopManager getInstance() {
        if (cacheShopManager == null) {
            cacheShopManager = new CacheShopManager();
        }
        return cacheShopManager;
    }

    //购物车数据源
    private List<CartBean.ResultBean> carts;
    private List<ICartChange> cartChanges = new LinkedList<>();

    public void showCart() {
        RetrofitManager.getHttpApiService()
                .showCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CartBean cartBean) {
                        if (cartBean.getCode().equals("200")) {
                            carts = cartBean.getResult();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("CacheShopManager", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public synchronized void registerCart(ICartChange iCartChange){
        cartChanges.add(iCartChange);
    }
    public synchronized void unRegisterCart(ICartChange iCartChange){
        cartChanges.remove(iCartChange);
    }

    public List<CartBean.ResultBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartBean.ResultBean> carts) {
        this.carts = carts;
        for (ICartChange cartChange : cartChanges) {
            cartChange.onShowCart(carts);
        }
    }

    public interface ICartChange{
        void onShowCart(List<CartBean.ResultBean> carts);
    }


}
