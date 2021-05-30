package com.example.myapplication.particulars;

import android.util.Log;

<<<<<<< HEAD
import com.example.framework.manager.CaCheMannager;
import com.example.common.log.LogUtil;
=======
import com.example.common.log.LogUtil;
import com.example.framework.manager.CaCheMannager;

>>>>>>> xsp
import com.example.net.retrogit.RetrofitManager;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.AddShoppingCartBean;
import com.example.net.bean.ShoppingCartBean;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddShoppingCartPresenter extends BasePresenter<IAddShoppingCartView> {

    public AddShoppingCartPresenter(IAddShoppingCartView view) {
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
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<AddShoppingCartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull AddShoppingCartBean addShoppingCartBean) {
                        if (mView != null) {
                            mView.onAddShoppingCart(addShoppingCartBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            LogUtil.e(e.getMessage());
                        }
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
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hideLoading();
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
                            LogUtil.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    //获取购物车数据
    public synchronized void getShoppingCart() {
        RetrofitManager.getApi().getShoppingCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingCartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShoppingCartBean shoppingCartBean) {
                        //购物车数据
                        CaCheMannager.getInstance().setShoppingCartBeanList(shoppingCartBean.getResult());
                       CaCheMannager.getInstance().showShoppingData();
                        Log.d("ShoppingCartActivity", "123123");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ShoppingCartActivity", "1231234");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
