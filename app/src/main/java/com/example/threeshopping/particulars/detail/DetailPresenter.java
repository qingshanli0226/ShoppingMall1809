package com.example.threeshopping.particulars.detail;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.common.SignUtil;
import com.example.common.SpUtil;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
import com.example.net.bean.UpdateProductNumBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class DetailPresenter extends BasePresenter<IDetailView> {
    public DetailPresenter(IDetailView iDetailView) {
        attchView(iDetailView);
    }
    //添加数据
    public void addProduct(ProductBean productBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productId",productBean.getProductId());
        jsonObject.put("productNum",productBean.getProductNum());
        jsonObject.put("productName",productBean.getProductName());
        jsonObject.put("url",productBean.getUrl());
        jsonObject.put("productPrice",productBean.getProductPrice());
        jsonObject.put("productSelected",false);

        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign",sign);
        SignUtil.encryptJsonParamsByBase64(jsonObject);

        String s = new Gson().toJson(productBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .addProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (mView != null) {
                            mView.onAddCart(selectBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



  //检查数量
    public void inventory(CartBean.ResultBean resultBean) {

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("productId",resultBean.getProductId());
        treeMap.put("productNum",resultBean.getProductNum());
        String sign = SignUtil.generateSign(treeMap);
        treeMap.put("sign",sign);
        TreeMap<String, String> map = SignUtil.encryptParamsByBase64(treeMap);
        //.inventory(map)
        //.inventory(Integer.parseInt(resultBean.getProductId()),Integer.parseInt(resultBean.getProductNum()))
        RetrofitManager.getHttpApiService()
                .inventory(Integer.parseInt(resultBean.getProductId()),Integer.parseInt(resultBean.getProductNum()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (mView != null){
                            mView.onInventory(selectBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                      if (mView != null){
                          mView.showError(e.getMessage());
                      }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
