package com.example.framework.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.MainThread;

import com.example.net.BuildConfig;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
                        Log.i("TAG", "onNext: "+cartBean);
                        if (cartBean.getCode().equals("200")) {
                            setCarts(cartBean.getResult());
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

    public void updateProductSelect(CartBean.ResultBean resultBean){
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");

        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .updateProductSelect(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            Log.i("zyb", "onNext: 成功");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zyb", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void selectAll(boolean isAll){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected",isAll);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");

        RequestBody requestBody = RequestBody.create(parse, jsonObject.toString());
        RetrofitManager.getHttpApiService()
                .selectAll(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            Log.i("zyb", "onNext: 成功");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zyb", e.getMessage());
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

    public synchronized void setCarts(List<CartBean.ResultBean> carts) {
        this.carts = carts;
        Log.i("zyb", "setCarts: "+carts);
        for (ICartChange cartChange : cartChanges) {
            cartChange.onShowCart(carts);
        }
    }

    public interface ICartChange{
        void onShowCart(List<CartBean.ResultBean> carts);
    }


}
