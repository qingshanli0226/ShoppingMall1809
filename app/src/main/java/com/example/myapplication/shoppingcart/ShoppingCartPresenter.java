package com.example.myapplication.shoppingcart;

import android.util.Log;

import com.example.net.retrofit.RetrofitManager;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShoppingCartPresenter extends BasePresenter<IShoppingCartView> {
    public ShoppingCartPresenter(IShoppingCartView view) {
        attView(view);
    }

    //修改全选按钮
    public void updatateAllSelect(boolean isChe) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isChe);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getApi()
                .updateAllSelected(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onupdateCheck(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ShoppingCartPresenter错了", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //修改单个选择
    public void updateShoppingSlect(String id, boolean isChe, String name, String url, String price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", id);
            jsonObject.put("productSelected", isChe);
            jsonObject.put("productName", name);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getApi()
                .getUpdateProductSelected(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onupdateShoppingSelect(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ShoppingCartPresenter错了", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //修改数量
    public void updateShoppingNum(String id, String num, String name, String url, String price) {
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
        RetrofitManager.getApi()
                .updataShoppingNum(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onShoppingSetNum(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ShoppingCartPresenter错了", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //库存
    public void getInventory(String id, String num) {
        RetrofitManager.getApi().getInventory(id,num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
    //删除
    public void deleteOneShopping(String id, String num, String name, String url, String price) {
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
        RetrofitManager.getApi()
                .deleteOneShopping(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onDeleteOneShopping(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ShoppingCartPresenter错了", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //删除多个
    public void RemoveManvProduct(List<ShoppingCartBean.ResultBean> list) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            ShoppingCartBean.ResultBean resultBean = list.get(i);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",resultBean.getProductId());
                jsonObject.put("productNum",resultBean.getProductNum());
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("url",resultBean.getUrl());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        RetrofitManager.getApi()
                .getRemoveManyProduct(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onRemoveManvProduct(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ShoppingCartPresenter错了", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
