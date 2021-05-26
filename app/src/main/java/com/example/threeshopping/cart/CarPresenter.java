package com.example.threeshopping.cart;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.framework.manager.CacheShopManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.SelectBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CarPresenter extends BasePresenter<ICarView> {

    public CarPresenter(ICarView iCarView) {
        attchView(iCarView);
    }





    //单选
    public synchronized void updateProductSelect(int position,CartBean.ResultBean resultBean) {
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
                            //更改数据
                            CacheShopManager.getInstance().setCheck(position,resultBean.isProductSelected());
                            if (mView != null) {
                                mView.onCheck(position,CacheShopManager.getInstance().getCarts().get(position).isProductSelected());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //全选
    public synchronized void selectAll(boolean isAll) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isAll);
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
                            //全选更改数据源
                            CacheShopManager.getInstance().setChackAll(isAll);
                            if (mView != null) {
                                mView.onCheckAll(isAll);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //检查数量
    public synchronized void inventory(int position,CartBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
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
                        if (selectBean.getCode().equals("200")) {
                            //服务端
                            upDateNum(position,resultBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //更新服务数量
    public void upDateNum(int position,CartBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            if (mView != null) {
                                CacheShopManager.getInstance().setNum(position,resultBean.getProductNum());
                                mView.onNum(position);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
