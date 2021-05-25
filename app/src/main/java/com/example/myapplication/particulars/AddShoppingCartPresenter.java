package com.example.myapplication.particulars;

import android.content.Context;
import android.util.Log;

import com.example.framework.SpUtil;
import com.example.net.RetrofitManager;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class AddShoppingCartPresenter extends BasePresenter<AddShoppingCartView> {

    public AddShoppingCartPresenter(AddShoppingCartView view) {
        attView(view);
    }

    //购物车
    public void getAddShoppingCart(String id, String num, String name, String url, String price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", id);
            jsonObject.put("productNum", num);
            jsonObject.put("productName", name);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getApi().getAddShoppingCart(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (mView != null) {
                            add(disposable);
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<ShoppingCartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShoppingCartBean shoppingCartBean) {
                        if (mView != null) {
                            mView.onAddShoppingCart(shoppingCartBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            Log.d("购物车错误AddShoppingCartPresente", e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //库存
    public void getInventory(String id, String num) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", id);
            jsonObject.put("productNum", num);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ResponseBody body = ResponseBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getApi().getInventory(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onIsInventory(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            Log.d("购物车错误AddShoppingCartPresente", e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
